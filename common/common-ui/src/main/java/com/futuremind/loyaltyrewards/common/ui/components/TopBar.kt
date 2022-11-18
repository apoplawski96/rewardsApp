package com.futuremind.loyaltyrewards.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.common.ui.R
import com.futuremind.loyaltyrewards.common.ui.theme.LocalTypography

@Composable
fun TopBar(
    title: String,
    onBack: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .height(72.dp)
            .padding(horizontal = 16.dp)
            .statusBarsPadding()
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = stringResource(R.string.back)
            )
        }
        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = LocalTypography.current.HeaderM
        )
    }    
}