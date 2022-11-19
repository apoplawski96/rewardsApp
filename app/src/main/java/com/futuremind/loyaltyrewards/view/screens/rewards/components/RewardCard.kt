package com.futuremind.loyaltyrewards.view.screens.rewards.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.common.ui.components.AsyncImage
import com.futuremind.loyaltyrewards.common.ui.components.IconButtonSmall
import com.futuremind.loyaltyrewards.common.ui.theme.LocalTypography
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward

// Verify shape
@Composable
fun RewardCard(reward: Reward) {
    Card(
        shape = RoundedCornerShape(size = 8.dp),
        modifier = Modifier.size(height = 256.dp, width = 128.dp),
    ) {
        Column {
            RewardImage(url = reward.coverUrl, modifier = Modifier.weight(2f))
            RewardInfoSection(reward = reward, onButtonClick = {  })
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
) {
    Column {
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

