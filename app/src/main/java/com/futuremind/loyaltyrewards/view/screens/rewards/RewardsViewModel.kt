package com.futuremind.loyaltyrewards.view.screens.rewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetRewards
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class RewardsViewModel(
    private val getRewards: GetRewards,
) : ViewModel() {

    sealed interface ViewState {

        object Loading : ViewState

        object Error : ViewState

        data class DataLoaded(
            val rewards: List<Reward>,
            val points: Int,
        ) : ViewState
    }

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun initialize() {
        viewModelScope.launch {
            when(val rewards = getRewards()) {
                is GetRewards.Result.Success -> {
                    _viewState.update {
                        ViewState.DataLoaded(
                            rewards = rewards.items,
                            points = 100
                        )
                    }
                }
                is GetRewards.Result.Failure -> {
                    _viewState.update {
                        ViewState.Error
                    }
                }
            }
        }
    }

    fun refresh() {
        TODO()
    }
}