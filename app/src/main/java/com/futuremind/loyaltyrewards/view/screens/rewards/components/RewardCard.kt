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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.common.ui.components.AsyncImage
import com.futuremind.loyaltyrewards.common.ui.components.GradientButtonWithIcon
import com.futuremind.loyaltyrewards.common.ui.components.VerticalSpacer
import com.futuremind.loyaltyrewards.common.ui.theme.AppColors
import com.futuremind.loyaltyrewards.common.ui.theme.LocalTypography
import com.futuremind.loyaltyrewards.common.ui.theme.Palette
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward

@Composable
fun RewardCard(
    reward: Reward,
    onRewardClick: () -> Unit,
    clickActive: Boolean,
) {
    Card(
        shape = RoundedCornerShape(size = 8.dp),
        modifier = Modifier.size(height = 256.dp, width = 196.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clickable { if (clickActive) onRewardClick() },
        ) {
            RewardImage(
                url = reward.coverUrl,
                modifier = Modifier
                    .weight(2f)
                    .blur(getImageBlur(reward.state))
            )
            VerticalSpacer(height = 16.dp)
            RewardBottomSection(
                reward = reward,
                modifier = Modifier.weight(1f),
                onButtonClick = { onRewardClick() },
                buttonActive = clickActive,
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
private fun RewardBottomSection(
    reward: Reward,
    onButtonClick: () -> Unit,
    buttonActive: Boolean,
    modifier: Modifier = Modifier,
) {
    // TODO: Add remember?
    val buttonBackground = when (reward.state) {
        Reward.State.AVAILABLE -> {
            Brush.verticalGradient(
                0.0f to Palette.grayDark,
                1f to Palette.grayDark,
            )
        }
        Reward.State.UNAVAILABLE -> {
            Brush.verticalGradient(
                0.0f to Palette.grayLight,
                1f to Palette.grayLight,
            )
        }
        Reward.State.ACTIVATED -> {
            AppColors.gradientPrimaryButton
        }
    }

    val buttonIcon = when (reward.state) {
        Reward.State.AVAILABLE -> R.drawable.ic_unlock
        Reward.State.UNAVAILABLE -> R.drawable.ic_lock
        Reward.State.ACTIVATED -> R.drawable.ic_check
    }

    val textColor = when (reward.state) {
        Reward.State.AVAILABLE, Reward.State.ACTIVATED -> Palette.black
        Reward.State.UNAVAILABLE -> Palette.grayLight
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = reward.name,
            style = LocalTypography.current.HeaderM, // TODO update,
            color = textColor
        )
        VerticalSpacer(height = 8.dp)
        GradientButtonWithIcon(
            label = getPluralString(id = R.plurals.points, count = reward.pointsCost),
            iconResId = buttonIcon,
            contentDescription = "Reward item",
            onClick = { onButtonClick() },
            buttonBackground = buttonBackground,
            isProcessing = !buttonActive,
        )
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

private fun getImageBlur(rewardState: Reward.State): Dp =
    if (rewardState == Reward.State.UNAVAILABLE) {
        4.dp
    } else {
        0.dp
    }

