package com.futuremind.loyaltyrewards.view.screens.rewards.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.common.ui.components.AsyncImage
import com.futuremind.loyaltyrewards.common.ui.components.IconButtonSmall
import com.futuremind.loyaltyrewards.common.ui.components.VerticalSpacer
import com.futuremind.loyaltyrewards.common.ui.theme.LocalTypography
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward

// Verify shape
@Composable
fun RewardCard(reward: Reward) {
    Card(
        shape = RoundedCornerShape(size = 8.dp),
        modifier = Modifier.size(height = 256.dp, width = 196.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            RewardImage(url = reward.coverUrl, modifier = Modifier.weight(2f))
            VerticalSpacer(height = 8.dp)
            RewardInfoSection(reward = reward, onButtonClick = {  }, modifier = Modifier.weight(1f))
            VerticalSpacer(height = 8.dp)
        }
    }
}

@Composable
private fun RewardImage(
    url: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        url = url,
        contentDescription = "Reward item",
        modifier = modifier,
    )
}

@Composable
private fun RewardInfoSection(
    reward: Reward,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = reward.name,
            style = LocalTypography.current.BodyL
        )
        IconButtonSmall(
            iconPainter = painterResource(R.drawable.ic_card),
            onClick = { onButtonClick() }
        )
    }
}

