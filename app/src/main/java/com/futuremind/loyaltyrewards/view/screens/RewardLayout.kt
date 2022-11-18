package com.futuremind.loyaltyrewards.view.screens

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.view.RewardsViewModel
import com.futuremind.loyaltyrewards.common.ui.components.ButtonLarge
import com.futuremind.loyaltyrewards.common.ui.components.ColoredCard
import com.futuremind.loyaltyrewards.common.ui.components.IconButtonSmall
import com.futuremind.loyaltyrewards.common.ui.components.TopBar
import com.futuremind.loyaltyrewards.common.ui.theme.LocalColors
import com.futuremind.loyaltyrewards.common.ui.theme.LocalTypography
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun RewardLayout(viewModel: RewardsViewModel = viewModel()) {
    val points by viewModel.loyaltyPointsFlow.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    RewardLayout(
        points = points,
        isLoading = isLoading,
        onRefresh = { viewModel.refresh() }
    )
}

@Composable
private fun RewardLayout(
    points: Int?,
    isLoading: Boolean,
    onRefresh: () -> Unit
) {

    val errorSnackbarState = remember { SnackbarHostState() }

    val scrollState = rememberScrollState()

    Box {
        Column {
            TopBar(
                title = stringResource(R.string.rewards),
                onBack = { /* not part of task */ }
            )
            SwipeRefresh(
                state = rememberSwipeRefreshState(isLoading),
                onRefresh = onRefresh,
            ) {
                Column(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .background(LocalColors.current.gradientLight)
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    GreetingRow("FM Candidate") //not part of task
                    Spacer(modifier = Modifier.height(24.dp))
                    PointsSection(points = points)
                    Spacer(modifier = Modifier.height(24.dp))
                    ShareCard()
                }
            }
        }
        SnackbarHost(
            hostState = errorSnackbarState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .systemBarsPadding()
        )
    }

}

@Composable
fun GreetingRow(userName: String) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.hi_user, userName),
                style = LocalTypography.current.HeaderL.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = stringResource(R.string.welcome_to_club),
                style = LocalTypography.current.BodyL
            )
        }
        IconButtonSmall(
            iconPainter = painterResource(R.drawable.ic_card),
            onClick = { /* not part of task */ }
        )
    }
}

@Composable
fun PointsSection(
    points: Int?
) {

    val animatedPoints by animateIntAsState(targetValue = points ?: 0)
    val pointsText = when (points) {
        null -> "-"
        else -> animatedPoints.toString()
    }

    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(R.drawable.ic_gift), contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row {
                Text(
                    text = pointsText,
                    style = LocalTypography.current.HeaderL,
                    color = MaterialTheme.colors.primary
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.points),
                    style = LocalTypography.current.HeaderL
                )
            }
            Text(text = stringResource(R.string.redeem_points_subtitle))
        }
    }
}

@Composable
fun ShareCard() {
    ColoredCard {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.need_more_points_title),
                style = LocalTypography.current.HeaderM
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.need_more_points_body),
                style = LocalTypography.current.BodyM
            )
            Spacer(Modifier.height(16.dp))
            ButtonLarge(
                text = stringResource(R.string.share_invite_code),
                onClick = { /* not part of task */ },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
