package com.futuremind.loyaltyrewards.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.view.theme.LocalColors

@Composable
fun ColoredCard(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(brush = LocalColors.current.gradientCard)
            .padding(20.dp)
    ) {
        content()
    }
}