package com.futuremind.loyaltyrewards.feature.dogs.dependency.di

import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetRewards
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetRewardsActivationStatus
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetUserPoints
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.SwitchRewardActivationStatus
import com.futuremind.loyaltyrewards.feature.dogs.dependency.domain.GetFmRewards
import com.futuremind.loyaltyrewards.feature.dogs.dependency.domain.GetFmRewardsActivationStatus
import com.futuremind.loyaltyrewards.feature.dogs.dependency.domain.GetFmUserPoints
import com.futuremind.loyaltyrewards.feature.dogs.dependency.domain.SwitchFmRewardActivationStatus
import com.futuremind.loyaltyrewards.feature.dogs.dependency.domain.converter.ApiRewardsConverter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val fmRewardsModule = module {
    factoryOf(::GetFmRewards) bind GetRewards::class
    factoryOf(::GetFmUserPoints) bind GetUserPoints::class
    factoryOf(::SwitchFmRewardActivationStatus) bind SwitchRewardActivationStatus::class
    factoryOf(::GetFmRewardsActivationStatus) bind GetRewardsActivationStatus::class
    factoryOf(::RewardsApi)
    factoryOf(::ApiRewardsConverter)
}