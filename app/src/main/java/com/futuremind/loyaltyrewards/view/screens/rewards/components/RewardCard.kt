package com.futuremind.loyaltyrewards.view.screens.rewards.components

import androidx.annotation.PluralsRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.common.ui.components.AsyncImage
import com.futuremind.loyaltyrewards.common.ui.components.ButtonWithIcon
import com.futuremind.loyaltyrewards.common.ui.components.VerticalSpacer
import com.futuremind.loyaltyrewards.common.ui.theme.LocalTypography
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward

// Verify shape
@Composable
fun RewardCard(reward: Reward, onRewardClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(size = 8.dp),
        modifier = Modifier.size(height = 256.dp, width = 196.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clickable { onRewardClick() },
        ) {
            RewardImage(url = reward.coverUrl, modifier = Modifier.weight(2f))
            VerticalSpacer(height = 16.dp)
            RewardInfoSection(
                reward = reward,
                onButtonClick = { onRewardClick() },
                modifier = Modifier.weight(1f),
            )
            VerticalSpacer(height = 16.dp)
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
    Column(
        modifier = modifier then Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = reward.name,
            style = LocalTypography.current.BodyL // TODO update
        )
        ButtonWithIcon(
            label = getPluralString(id = R.plurals.points, count = reward.pointsCost),
            iconResId = R.drawable.ic_lock,
            contentDescription = "Reward item",
            onClick = { onButtonClick() })
    }
}

@Composable
private fun getPluralString(
    @PluralsRes id: Int,
    count: Int,
): String {
    val resources = LocalContext.current.resources
    return resources.getQuantityString(id, count, count)
}

