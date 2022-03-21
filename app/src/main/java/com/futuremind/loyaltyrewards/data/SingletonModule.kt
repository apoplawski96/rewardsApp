package com.futuremind.loyaltyrewards.data

import com.futuremind.loyaltyrewards.api.RewardsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.MainScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Singleton
    @Provides
    fun provide() = RewardsApi()

    @Singleton
    @Provides
    fun coroutineScope() = MainScope()

}