package com.futuremind.loyaltyrewards.feature.dogs.dependency.domain.usecase

import com.futuremind.loyaltyrewards.api.MockHttpException
import com.futuremind.loyaltyrewards.api.MockIoException
import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.common.util.coroutines.DispatcherProvider
import com.futuremind.loyaltyrewards.common.util.logger.DebugLogger
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetUserPoints
import com.futuremind.loyaltyrewards.feature.dogs.api.model.error.RewardsApiErrorCause
import kotlinx.coroutines.withContext

private const val LOGGER_TAG = "GetFmUserPoints"

internal class GetFmUserPoints(
    private val rewardsApi: RewardsApi,
    private val dispatcherProvider: DispatcherProvider,
    private val debugLogger: DebugLogger,
) : GetUserPoints {

    override suspend fun invoke(): GetUserPoints.Result = try {
        withContext(dispatcherProvider.io) {
            val userPoints = rewardsApi.getPoints().points

            debugLogger.log(LOGGER_TAG) { "User points fetched from API: $userPoints." }

            GetUserPoints.Result.Success(points = userPoints)
        }
    } catch (e: MockHttpException) {
        debugLogger.log(LOGGER_TAG) { "Failed with exception: $e." }

        GetUserPoints.Result.Failure(cause = RewardsApiErrorCause.INVALID_REQUEST)
    } catch (e: MockIoException) {
        debugLogger.log(LOGGER_TAG) { "Failed with exception: $e." }

        GetUserPoints.Result.Failure(cause = RewardsApiErrorCause.IO_EXCEPTION)
    } catch (e: Exception) {
        debugLogger.log(LOGGER_TAG) { "Failed with exception: $e." }

        GetUserPoints.Result.Failure(cause = RewardsApiErrorCause.UNKNOWN)
    }
}