package com.futuremind.loyaltyrewards.feature.dogs.dependency.domain

import com.futuremind.loyaltyrewards.api.ApiRewardActivationStatus
import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetRewards
import com.futuremind.loyaltyrewards.feature.dogs.dependency.domain.converter.ApiRewardsConverter

internal class GetFmRewards(
    private val rewardsApi: RewardsApi,
    private val apiRewardsConverter: ApiRewardsConverter,
) : GetRewards {

    // TODO: Handle exceptons properly
    override suspend fun invoke(): GetRewards.Result = try {
        // TODO: Make async
        val rewards = rewardsApi.getRewards()
        val rewardsActivationStatus: List<ApiRewardActivationStatus> = rewardsApi.getRewardsActivationStatus()
        val userPoints = rewardsApi.getPoints()
//
        val result = apiRewardsConverter.convert(
            apiRewards = rewards,
            activationStatus = rewardsActivationStatus,
            userPoints = userPoints.points
        )

        GetRewards.Result.Success(items = result)
    } catch (e: Exception) {
        GetRewards.Result.Failure
    }
}