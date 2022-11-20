package com.futuremind.loyaltyrewards.feature.dogs.api.model.error

interface RewardsApiException {
    val cause: RewardsApiErrorCause
}