package com.futuremind.loyaltyrewards.common.util.test

import com.futuremind.loyaltyrewards.common.util.coroutines.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class TestDispatcherExtension(
    private val testDispatcherProvider: DispatcherProvider = TestDispatcherProvider()
) : BeforeEachCallback, AfterEachCallback {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcherProvider.main)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}
