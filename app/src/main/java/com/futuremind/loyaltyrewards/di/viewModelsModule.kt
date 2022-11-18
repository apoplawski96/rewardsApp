package com.futuremind.loyaltyrewards.di

import com.futuremind.loyaltyrewards.view.screens.rewards.RewardsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelOf(::RewardsViewModel)
}