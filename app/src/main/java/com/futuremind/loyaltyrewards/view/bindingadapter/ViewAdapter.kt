package com.futuremind.loyaltyrewards.view.bindingadapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun setVisibility(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}