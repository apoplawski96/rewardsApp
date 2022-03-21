package com.futuremind.loyaltyrewards.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.view.theme.LocalColors
import com.futuremind.loyaltyrewards.view.theme.LocalTypography

@Composable
fun IconButtonSmall(
    iconPainter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
){
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(LocalColors.current.gradientPrimaryButton)
            .then(modifier)
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = contentDescription,
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ButtonLarge(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = Modifier
            .height(40.dp)
            .clip(MaterialTheme.shapes.large)
            .background(LocalColors.current.gradientPrimaryButton)
            .clickable { onClick() }
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = LocalTypography.current.HeaderM,
            color = MaterialTheme.colors.onPrimary
        )
    }
}
