package com.futuremind.loyaltyrewards.common.util.extension

fun executeIfNotCurrentlyProcessing(
    isProcessing: Boolean,
    block: () -> Unit
) {
    if (isProcessing.not()) {
        block()
    }
}