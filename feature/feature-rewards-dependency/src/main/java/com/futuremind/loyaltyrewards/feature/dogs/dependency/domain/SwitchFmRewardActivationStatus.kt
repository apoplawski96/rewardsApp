package com.futuremind.loyaltyrewards.feature.dogs.dependency.domain

import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.SwitchRewardActivationStatus
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward

internal class SwitchFmRewardActivationStatus(
    private val rewardsApi: RewardsApi,
) : SwitchRewardActivationStatus {

    override suspend fun invoke(reward: Reward): SwitchRewardActivationStatus.Result = try {
        rewardsApi.changeRewardActivationStatus(
            rewardId = reward.id,
            activationsCount = when(reward.state) {
                Reward.State.AVAILABLE -> 1
                Reward.State.UNAVAILABLE -> 0 // TODO: Check
                Reward.State.ACTIVATED -> 0
            },
        )
        SwitchRewardActivationStatus.Result.Success
    } catch (e: Exception) {
        SwitchRewardActivationStatus.Result.Failure
    }
}