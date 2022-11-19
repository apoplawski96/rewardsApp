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
        val rewardsActivationStatus: List<ApiRewardActivationStatus> = listOf()
//            rewardsApi.getRewardsActivationStatus()
        val result = apiRewardsConverter.convert(
            apiRewards = rewards,
            activationStatus = rewardsActivationStatus
        )

//        val statuses = rewardsApi.getRewardsActivationStatus()
//        println("2137, statuses: $statuses")

        GetRewards.Result.Success(items = result)
    } catch (e: Exception) {
        println("2137 - exception HERE: $e")
        GetRewards.Result.Failure
    }
}