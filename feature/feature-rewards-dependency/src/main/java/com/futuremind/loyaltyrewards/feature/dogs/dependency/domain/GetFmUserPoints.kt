package com.futuremind.loyaltyrewards.feature.dogs.dependency.domain

import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetUserPoints

internal class GetFmUserPoints(
    private val rewardsApi: RewardsApi,
) : GetUserPoints {

    override suspend fun invoke(): GetUserPoints.Result = try {
        GetUserPoints.Result.Success(points = rewardsApi.getPoints().points)
    } catch (e: Exception) {
        GetUserPoints.Result.Failure
    }
}