package com.futuremind.loyaltyrewards.feature.dogs.dependency.domain.converter

import com.futuremind.loyaltyrewards.api.ApiReward
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward

internal class ApiRewardsConverter {

    fun convert(apiRewards: List<ApiReward>): List<Reward> = apiRewards.map { apiReward ->
        Reward(
            id = apiReward.id,
            name = apiReward.name,
            coverUrl = apiReward.coverUrl,
            pointsCost = apiReward.pointsCost,
            activated = false
        )
    }
}