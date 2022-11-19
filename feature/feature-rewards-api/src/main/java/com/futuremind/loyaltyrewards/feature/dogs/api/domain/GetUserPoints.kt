package com.futuremind.loyaltyrewards.feature.dogs.api.domain

interface GetUserPoints {

    sealed interface Result {
        data class Success(val points: Int) : Result
        object Failure : Result
    }

    suspend operator fun invoke() : Result
}