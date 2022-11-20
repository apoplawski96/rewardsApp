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




class RewardsViewModel(
    private val getRewards: GetRewards,
    private val getUserPoints: GetUserPoints,
    private val switchRewardActivationStatus: SwitchRewardActivationStatus,
    private val getRewardsActivationStatus: GetRewardsActivationStatus,
    private val dispatcherProvider: DispatcherProvider,
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
    }

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _viewEvent: Channel<ViewEvent> = Channel()
    val viewEvent = _viewEvent.receiveAsFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun initialize() {
        fetchDataFromApi(initialization = true)
    }

    fun onRewardClick(reward: Reward) {
        viewModelScope.launch {
            _isLoading.update { true }
            switchRewardActivationStatus(reward = reward)

            fetchDataFromApi(initialization = false)
            _isLoading.update { false }
        }
    }

    fun refresh() {
        fetchDataFromApi(initialization = false)
    }

    private fun fetchDataFromApi(initialization: Boolean) {
        viewModelScope.launch {
            _isLoading.update { true }

            val getRewardsDeferred = async { getRewards() }
            val getUserPointsDeferred = async { getUserPoints() }

            val getRewardsResult = getRewardsDeferred.await()
            val getUserPointsResult = getUserPointsDeferred.await()

            if (getRewardsResult is GetRewards.Result.Success && getUserPointsResult is GetUserPoints.Result.Success) {
                _viewState.update {
                    _isLoading.update { false }
                    ViewState.DataLoaded(
                        rewards = getRewardsResult.items,
                        points = getUserPointsResult.points
                    )
                }
            } else {
                _isLoading.update { false }

                if (initialization) {
                    _viewState.update { ViewState.InitializationError }
                } else {
                    _viewEvent.send(ViewEvent.Error)
                }
            }
        }
    }
}