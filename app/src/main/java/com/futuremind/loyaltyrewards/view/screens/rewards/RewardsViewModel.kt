package com.futuremind.loyaltyrewards.view.screens.rewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.futuremind.loyaltyrewards.common.ui.model.LoadingState
import com.futuremind.loyaltyrewards.common.util.coroutines.DispatcherProvider
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetRewards
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetRewardsActivationStatus
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetUserPoints
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.SwitchRewardActivationStatus
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean


class RewardsViewModel(
    private val getRewards: GetRewards,
    private val getUserPoints: GetUserPoints,
    private val switchRewardActivationStatus: SwitchRewardActivationStatus,
) : ViewModel() {

    sealed interface ViewState {
        object InitializationError : ViewState
        object Loading : ViewState, LoadingState
        data class DataLoaded(
            val rewards: List<Reward>,
            val points: Int,
        ) : ViewState
    }

    sealed interface ViewEvent {
        object Error : ViewEvent
        object NoPointsAvailable : ViewEvent
    }

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _viewEvent: Channel<ViewEvent> = Channel()
    val viewEvent = _viewEvent.receiveAsFlow()

    private val _isProcessing = MutableStateFlow(false)
    val isProcessing = _isProcessing.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val isInitialized = AtomicBoolean(false)

    fun initialize() {
        if (isInitialized.compareAndSet(false, true)) {
            fetchDataFromApi(initialization = true)
        }
    }

    fun onRewardClick(reward: Reward) {
        viewModelScope.launch {
            if (reward.state == Reward.State.UNAVAILABLE) {
                _viewEvent.send(ViewEvent.NoPointsAvailable)
                return@launch
            }

            _isProcessing.update { true }

            when(switchRewardActivationStatus(reward = reward)) {
                SwitchRewardActivationStatus.Result.Success -> {
                    fetchDataFromApi(initialization = false)
                }
                SwitchRewardActivationStatus.Result.Failure -> {
                    _viewEvent.send(ViewEvent.Error)
                }
            }

            _isProcessing.update { false }
            _isRefreshing.update { false }
        }
    }

    fun refresh() {
        _isRefreshing.update { true }
        fetchDataFromApi(initialization = false)
    }

    private fun fetchDataFromApi(initialization: Boolean) {
        viewModelScope.launch {
            _isProcessing.update { true }

            val getRewardsDeferred = async { getRewards() }
            val getUserPointsDeferred = async { getUserPoints() }

            val getRewardsResult = getRewardsDeferred.await()
            val getUserPointsResult = getUserPointsDeferred.await()

            if (getRewardsResult is GetRewards.Result.Success && getUserPointsResult is GetUserPoints.Result.Success) {
                _viewState.update {
                    _isProcessing.update { false }
                    _isRefreshing.update { false }

                    ViewState.DataLoaded(
                        rewards = getRewardsResult.items,
                        points = getUserPointsResult.points
                    )
                }
            } else {
                _isProcessing.update { false }
                _isRefreshing.update { false }

                if (initialization) {
                    _viewState.update { ViewState.InitializationError }
                } else {
                    _viewEvent.send(ViewEvent.Error)
                }
            }
        }
    }
}