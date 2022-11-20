package com.futuremind.loyaltyrewards.common.util.test

import com.futuremind.loyaltyrewards.common.util.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@Suppress("FunctionName")
fun TestDispatcherProvider(
    dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
): DispatcherProvider = MTestDispatcherProvider(dispatcher)

class MTestDispatcherProvider(
    private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
) : DispatcherProvider {

    override val main: CoroutineDispatcher
        get() = dispatcher

    override val default: CoroutineDispatcher
        get() = dispatcher

    override val io: CoroutineDispatcher
        get() = dispatcher

    override val unconfined: CoroutineDispatcher
        get() = dispatcher
}
