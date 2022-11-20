package com.futuremind.loyaltyrewards.feature.dogs.dependency.domain

import com.futuremind.loyaltyrewards.api.MockHttpException
import com.futuremind.loyaltyrewards.api.MockIoException
import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.common.util.coroutines.DispatcherProvider
import com.futuremind.loyaltyrewards.common.util.logger.DebugLogger
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.SwitchRewardActivationStatus
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward
import kotlinx.coroutines.withContext

private const val LOGGER_TAG = "SwitchFmRewardActivationStatus"

internal class SwitchFmRewardActivationStatus(
    private val rewardsApi: RewardsApi,
    private val dispatcherProvider: DispatcherProvider,
    private val debugLogger: DebugLogger,
) : SwitchRewardActivationStatus {

    override suspend fun invoke(reward: Reward): SwitchRewardActivationStatus.Result = try {
        withContext(dispatcherProvider.io) {
            rewardsApi.changeRewardActivationStatus(
                rewardId = reward.id,
                activationsCount = when(reward.state) {
                    Reward.State.AVAILABLE -> 1
                    Reward.State.ACTIVATED -> 0
                    Reward.State.UNAVAILABLE -> return@withContext
                },
            )
        }

        debugLogger.log(LOGGER_TAG) { "Activation status switched for $reward." }

        SwitchRewardActivationStatus.Result.Success
    } catch (e: MockHttpException) {
        debugLogger.log(LOGGER_TAG) { "Failed with exception: $e." }

        SwitchRewardActivationStatus.Result.Failure
    }
    catch (e: MockIoException) {
        debugLogger.log(LOGGER_TAG) { "Failed with exception: $e." }

        SwitchRewardActivationStatus.Result.Failure
    }
    catch (e: Exception) {
        debugLogger.log(LOGGER_TAG) { "Failed with exception: $e." }

        SwitchRewardActivationStatus.Result.Failure
    }
}