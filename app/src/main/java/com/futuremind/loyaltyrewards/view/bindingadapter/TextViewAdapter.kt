package com.futuremind.loyaltyrewards.view.bindingadapter

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.futuremind.loyaltyrewards.R

@BindingAdapter("points")
fun setupPoints(view: TextView, points: Int) {
    val context = view.context
    val pointsStr = context.resources.getQuantityString(R.plurals.points, points, points)
    val color = ContextCompat.getColor(context, R.color.text_points_highlight)
    val coloredPoints = SpannableString(pointsStr)
    coloredPoints.setSpan(ForegroundColorSpan(color), 0, points.toString().length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    view.text = coloredPoints
}