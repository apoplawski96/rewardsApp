package com.futuremind.loyaltyrewards.common.util.logger

import android.util.Log

internal class LogCatDebugLogger : DebugLogger {

    override fun log(tag: String, message: () -> String) {
        Log.d(tag, message.invoke())
    }
}