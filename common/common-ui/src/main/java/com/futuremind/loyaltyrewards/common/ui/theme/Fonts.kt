package com.futuremind.loyaltyrewards.common.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.futuremind.loyaltyrewards.common.ui.R

val fonts = FontFamily(
    Font(R.font.mont_regular),
    Font(R.font.mont_regular_italic, style = FontStyle.Italic),
    Font(R.font.mont_bold, weight = FontWeight.Bold),
    Font(R.font.mont_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.mont_semibold, weight = FontWeight.SemiBold),
    Font(R.font.mont_semibold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
)

data class AppTypography(
    val HeaderL: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    val HeaderM: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    val HeaderS: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    ),
    val BodyL: TextStyle = TextStyle(
        fontSize = 16.sp
    ),
    val BodyM: TextStyle = TextStyle(
        fontSize = 12.sp
    ),
)

val LocalTypography = compositionLocalOf { AppTypography() }