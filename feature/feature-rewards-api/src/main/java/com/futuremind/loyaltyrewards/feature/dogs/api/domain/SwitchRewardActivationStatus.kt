package com.futuremind.loyaltyrewards.feature.dogs.api.domain

import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward

interface SwitchRewardActivationStatus {

    sealed interface Result {
        object Success : Result
        object Failure : Result
    }

    suspend operator fun invoke(reward: Reward) : Result
}