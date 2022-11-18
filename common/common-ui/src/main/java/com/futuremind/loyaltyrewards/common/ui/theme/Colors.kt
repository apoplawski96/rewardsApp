package com.futuremind.loyaltyrewards.common.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object AppColors {
    val gradientLight: Brush = Brush.verticalGradient(
        0f to Palette.silver,
        0.2f to Palette.white
    )
    val gradientPrimaryButton: Brush = Brush.linearGradient(
        0f to Palette.pink,
        1f to Palette.peach
    )
    val gradientCard: Brush = Brush.radialGradient(
        0f to Palette.silver,
        1f to Palette.peach,
        center = Offset(40f, -100f),
        radius = 4200f
    )
}

object Palette {
    val black = Color(0xFF2F2D2B)
    val white = Color(0xFFFFFFFF)
    val silver = Color(0xFFE8ECF8)
    val alabaster = Color(0xFFF4F5F9)
    val pink = Color(0xFFEB456C)
    val peach = Color(0xFFF56C72)
}

val LocalColors = staticCompositionLocalOf { AppColors }
