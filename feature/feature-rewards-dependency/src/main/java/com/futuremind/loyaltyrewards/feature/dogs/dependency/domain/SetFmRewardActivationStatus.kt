package com.futuremind.loyaltyrewards.feature.dogs.dependency.domain

import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.SetRewardActivationStatus

internal class SetFmRewardActivationStatus(
    private val rewardsApi: RewardsApi,
) : SetRewardActivationStatus {

    override suspend fun invoke(id: Int): SetRewardActivationStatus.Result = try {
        rewardsApi.changeRewardActivationStatus(rewardId = id, activationsCount = 1)
        SetRewardActivationStatus.Result.Success
    } catch (e: Exception) {
        SetRewardActivationStatus.Result.Failure
    }
}