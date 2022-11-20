package com.futuremind.loyaltyrewards.common.util.logger

interface DebugLogger {

    fun log(tag: String, message: () -> String)
}