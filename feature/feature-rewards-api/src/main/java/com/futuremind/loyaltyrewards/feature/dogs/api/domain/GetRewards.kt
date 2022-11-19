package com.futuremind.loyaltyrewards.feature.dogs.api.domain

import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward

interface GetRewards {

    sealed interface Result {
        data class Success(val items: List<Reward>) : Result
        object Failure : Result
    }

    suspend operator fun invoke() : Result
}