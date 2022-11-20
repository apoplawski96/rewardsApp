package com.futuremind.loyaltyrewards.common.util.di

import com.futuremind.loyaltyrewards.common.util.coroutines.AndroidDispatcherProvider
import com.futuremind.loyaltyrewards.common.util.coroutines.DispatcherProvider
import com.futuremind.loyaltyrewards.common.util.logger.DebugLogger
import com.futuremind.loyaltyrewards.common.util.logger.LogCatDebugLogger
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {
    factoryOf(::AndroidDispatcherProvider) bind DispatcherProvider::class
    singleOf(::LogCatDebugLogger) bind DebugLogger::class
}