package com.futuremind.loyaltyrewards.feature.dogs.dependency.di

import com.futuremind.loyaltyrewards.api.RewardsApi
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetRewards
import com.futuremind.loyaltyrewards.feature.dogs.dependency.domain.GetFmRewards
import com.futuremind.loyaltyrewards.feature.dogs.dependency.domain.converter.ApiRewardsConverter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val fmRewardsModule = module {
    factoryOf(::GetFmRewards) bind GetRewards::class
    factoryOf(::RewardsApi)
    factoryOf(::ApiRewardsConverter)
}