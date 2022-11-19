package com.futuremind.loyaltyrewards.feature.dogs.dependency.domain.converter

import com.futuremind.loyaltyrewards.api.ApiReward
import com.futuremind.loyaltyrewards.api.ApiRewardActivationStatus
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward

internal class ApiRewardsConverter {

    fun convert(
        apiRewards: List<ApiReward>,
        activationStatus: List<ApiRewardActivationStatus>,
    ): List<Reward> = apiRewards.map { apiReward ->
        Reward(
            id = apiReward.id,
            name = apiReward.name,
            coverUrl = apiReward.coverUrl,
            pointsCost = apiReward.pointsCost,
            activated = apiReward.isActivated(activationStatus)
        )
    }

    private fun ApiReward.isActivated(activationsStatus: List<ApiRewardActivationStatus>): Boolean {
        val rewardActivationStatus = activationsStatus.firstOrNull { activationStatus ->
            activationStatus.id == this.id
        }
        val activatedCount = rewardActivationStatus?.activatedCount ?: return false

        return activatedCount > 0
    }
}