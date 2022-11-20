package com.futuremind.loyaltyrewards.common.ui.components

import com.futuremind.loyaltyrewards.common.ui.model.LoadingState

fun executeIfNotCurrentlyProcessing(
    state: Any,
    block: () -> Unit
) {
    if (state !is LoadingState) {
        block()
    }
}