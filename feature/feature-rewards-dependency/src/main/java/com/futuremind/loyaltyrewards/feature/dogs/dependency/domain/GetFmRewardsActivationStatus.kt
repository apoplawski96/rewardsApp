package com.futuremind.loyaltyrewards.feature.dogs.dependency.domain

import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetRewardsActivationStatus

internal class GetFmRewardsActivationStatus(
    private val rewardsApi: RewardsApi,
) : GetRewardsActivationStatus {

    override suspend fun get() {
        val result = rewardsApi.getRewardsActivationStatus()
    }
}