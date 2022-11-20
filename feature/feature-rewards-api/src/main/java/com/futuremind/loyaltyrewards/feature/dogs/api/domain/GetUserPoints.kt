package com.futuremind.loyaltyrewards.feature.dogs.api.domain

import com.futuremind.loyaltyrewards.feature.dogs.api.model.error.RewardsApiErrorCause
import com.futuremind.loyaltyrewards.feature.dogs.api.model.error.RewardsApiException

interface GetUserPoints {

    sealed interface Result {
        data class Success(val points: Int) : Result
        data class Failure(
            override val cause: RewardsApiErrorCause
        ) : Result, RewardsApiException
    }

    suspend operator fun invoke(): Result
}