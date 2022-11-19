package com.futuremind.loyaltyrewards.view.screens.rewards.model

import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward

data class UIReward(
    val reward: Reward,
    val mode: Mode,
) {
    enum class Mode { ACTIVATED, AVAILABLE, UNAVAILABLE; }
}
