package com.futuremind.loyaltyrewards.view.helpers

import android.app.Activity
import android.view.View
import androidx.core.content.ContextCompat
import com.futuremind.loyaltyrewards.R
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnack(message: String, anchorView: View, duration: Int = Snackbar.LENGTH_LONG) =
    Snackbar.make(anchorView, message, duration)
        .setBackgroundTint(ContextCompat.getColor(this, R.color.background_reverse))
        .setTextColor(ContextCompat.getColor(this, R.color.text_primary_reverse))
        .show()