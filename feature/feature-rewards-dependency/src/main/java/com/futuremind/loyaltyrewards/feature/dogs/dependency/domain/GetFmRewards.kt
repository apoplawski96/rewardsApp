package com.futuremind.loyaltyrewards.feature.dogs.dependency.domain

import com.futuremind.loyaltyrewards.api.ApiRewardActivationStatus
import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.common.util.coroutines.DispatcherProvider
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetRewards
import com.futuremind.loyaltyrewards.feature.dogs.dependency.domain.converter.ApiRewardsConverter
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

internal class GetFmRewards(
    private val rewardsApi: RewardsApi,
    private val apiRewardsConverter: ApiRewardsConverter,
    private val dispatcherProvider: DispatcherProvider,
) : GetRewards {

    // TODO: Handle exceptons properly
    override suspend fun invoke(): GetRewards.Result = try {
        withContext(dispatcherProvider.io) {
            val rewardsDeferred = async { rewardsApi.getRewards() }
            val rewardsActivationStatusDeferred = async { rewardsApi.getRewardsActivationStatus() }
            val userPointsDeferred = async { rewardsApi.getPoints() }

            val rewards = rewardsDeferred.await()
            val rewardsActivationStatus = rewardsActivationStatusDeferred.await()
            val userPoints = userPointsDeferred.await()

            val result = apiRewardsConverter.convert(
                apiRewards = rewards,
                activationStatus = rewardsActivationStatus,
                userPoints = userPoints.points
            )

            GetRewards.Result.Success(items = result)
        }
    } catch (e: Exception) {
        GetRewards.Result.Failure
    }
}