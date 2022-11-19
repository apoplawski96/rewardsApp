package com.futuremind.loyaltyrewards.feature.dogs.api.domain

interface SetRewardActivationStatus {

    sealed interface Result {
        object Success : Result
        object Failure : Result
    }

    suspend operator fun invoke(id: Int) : Result
}