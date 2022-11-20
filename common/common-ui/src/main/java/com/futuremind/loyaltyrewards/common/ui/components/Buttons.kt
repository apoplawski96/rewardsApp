package com.futuremind.loyaltyrewards.common.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.common.ui.theme.LocalColors
import com.futuremind.loyaltyrewards.common.ui.theme.LocalTypography
import com.futuremind.loyaltyrewards.common.ui.theme.Palette

@Composable
fun IconButtonSmall(
    iconPainter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
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
) {
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

@Composable
fun GradientButtonWithIcon(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int,
    contentDescription: String?,
    buttonBackground: Brush,
    isProcessing: Boolean = true,
) {
    Row(
        modifier = Modifier
            .height(46.dp)
            .wrapContentWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(MaterialTheme.shapes.large)
            .background(brush = buttonBackground)
            .clickable { if (isProcessing.not()) onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        HorizontalSpacer(width = 16.dp)
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = contentDescription,
            tint = Palette.white
        )
        HorizontalSpacer(width = 8.dp)
        Text(
            text = label,
            style = LocalTypography.current.HeaderS,
            color = MaterialTheme.colors.onPrimary,
        )
        HorizontalSpacer(width = 16.dp)
    }
}
