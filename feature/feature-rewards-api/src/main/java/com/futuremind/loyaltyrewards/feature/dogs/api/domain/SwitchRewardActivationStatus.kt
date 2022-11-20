package com.futuremind.loyaltyrewards.feature.dogs.api.domain

import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward
import com.futuremind.loyaltyrewards.feature.dogs.api.model.error.RewardsApiErrorCause
import com.futuremind.loyaltyrewards.feature.dogs.api.model.error.RewardsApiException

interface SwitchRewardActivationStatus {

    sealed interface Result {
        object Success : Result
        data class Failure(
            override val cause: RewardsApiErrorCause
        ) : Result, RewardsApiException
    }

    suspend operator fun invoke(reward: Reward): Result
}