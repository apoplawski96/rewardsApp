package com.futuremind.loyaltyrewards.view

import androidx.lifecycle.ViewModel
import com.futuremind.loyaltyrewards.domain.Reward
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class RewardsViewModel @Inject constructor() : ViewModel() {

    private val _isLoading = MutableStateFlow(false)

    val loyaltyPointsFlow = MutableStateFlow(100) //TODO
    val rewards = MutableStateFlow(listOf<Reward>()) //TODO
    val isLoading = _isLoading.asStateFlow()

    fun refresh() {
        TODO()
    }

}