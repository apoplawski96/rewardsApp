package com.futuremind.loyaltyrewards.common.util.di

import com.futuremind.loyaltyrewards.common.util.coroutines.AndroidDispatcherProvider
import com.futuremind.loyaltyrewards.common.util.coroutines.DispatcherProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {
    factoryOf(::AndroidDispatcherProvider) bind DispatcherProvider::class
}