package com.futuremind.loyaltyrewards.view.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.futuremind.loyaltyrewards.domain.Reward
import com.futuremind.loyaltyrewards.domain.dashboard.DashboardItem
import com.futuremind.loyaltyrewards.domain.dashboard.DashboardItem.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {

    private val _isLoading = MutableStateFlow(false)

    private val loyaltyPointsFlow = MutableStateFlow(100) //TODO
    private val rewards = MutableStateFlow(listOf<Reward>()) //TODO
    val isLoading = _isLoading.asStateFlow()

    //TODO
    val dashboardFlow = loyaltyPointsFlow
        .map { points -> prepareDashboardItems(points) }
        .stateIn(viewModelScope, Lazily, listOf())

    fun refresh() {
        TODO()
    }

    private fun prepareDashboardItems(
        points: Int
    ): List<DashboardItem> = listOf(
        SectionTitle("FM Candidate"),
        CounterLoop(points),
        ShareBanner,
    )

}
