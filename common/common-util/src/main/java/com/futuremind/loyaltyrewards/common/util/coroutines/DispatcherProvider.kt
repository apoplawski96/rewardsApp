package com.futuremind.loyaltyrewards.common.util.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    val default: CoroutineDispatcher

    val io: CoroutineDispatcher

    val main: CoroutineDispatcher

    val unconfined: CoroutineDispatcher
}