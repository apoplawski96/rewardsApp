package com.futuremind.loyaltyrewards.view.screens.rewards

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.common.ui.components.*
import com.futuremind.loyaltyrewards.common.ui.theme.LocalColors
import com.futuremind.loyaltyrewards.common.ui.theme.LocalTypography
import com.futuremind.loyaltyrewards.common.util.extension.executeIfNotCurrentlyProcessing
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward
import com.futuremind.loyaltyrewards.view.screens.rewards.components.RewardCard
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.getViewModel


@Composable
fun RewardsScreen(viewModel: RewardsViewModel = getViewModel()) {

    val errorSnackbarState = remember { SnackbarHostState() }

    val viewState by viewModel.viewState.collectAsState()
    val isProcessing by viewModel.isProcessing.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    val errorMessage = stringResource(id = R.string.something_went_wrong)
    val notEnoughPointsMessage = stringResource(id = R.string.not_enough_points)

    LaunchedEffect(null) {
        viewModel.initialize()
        viewModel.viewEvent.collect { event ->
            when (event) {
                RewardsViewModel.ViewEvent.Error -> {
                    errorSnackbarState.showSnackbar(errorMessage)
                }
                RewardsViewModel.ViewEvent.NoPointsAvailable -> {
                    errorSnackbarState.showSnackbar(notEnoughPointsMessage)
                }
            }
        }
    }

    RewardsScreenContent(
        viewState = viewState,
        errorSnackbarState = errorSnackbarState,
        isProcessing = isProcessing,
        isRefreshing = isRefreshing,
        onRefresh = {
            viewModel.refresh()
        },
        onRewardClick = { reward ->
            executeIfNotCurrentlyProcessing(isProcessing) {
                viewModel.onRewardClick(reward)
            }
        },
        onRetryInitialize = {
            viewModel.retryInitialize()
        }
    )
}

@Composable
private fun RewardsScreenContent(
    viewState: RewardsViewModel.ViewState,
    errorSnackbarState: SnackbarHostState,
    onRefresh: () -> Unit,
    onRetryInitialize: () -> Unit,
    onRewardClick: (Reward) -> Unit,
    isProcessing: Boolean,
    isRefreshing: Boolean,
) {
    val scrollState = rememberScrollState()

    Box {
        Column {
            TopBar(
                title = stringResource(R.string.rewards),
                onBack = { /* not part of task */ }
            )
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                onRefresh = onRefresh,
            ) {
                when (viewState) {
                    is RewardsViewModel.ViewState.DataLoaded -> {
                        RewardsLayout(
                            viewState = viewState,
                            scrollState = scrollState,
                            onRewardClick = onRewardClick,
                            isProcessing = isProcessing,
                        )
                    }
                    is RewardsViewModel.ViewState.InitializationError -> {
                        InitializationErrorLayout(
                            scrollState = scrollState,
                            onRetryInitialize = onRetryInitialize
                        )
                    }
                    is RewardsViewModel.ViewState.Loading -> {
                        LoadingLayout()
                    }
                }
            }
        }
        SnackbarHost(
            hostState = errorSnackbarState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .systemBarsPadding()
                .padding(bottom = 48.dp)
        )
    }
}

@Composable
private fun RewardsLayout(
    viewState: RewardsViewModel.ViewState.DataLoaded,
    scrollState: ScrollState,
    onRewardClick: (Reward) -> Unit,
    isProcessing: Boolean
) {
    Box {
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
            PointsSection(points = viewState.points)
            Spacer(modifier = Modifier.height(24.dp))
            RewardsSection(
                rewards = viewState.rewards,
                onRewardClick = onRewardClick,
                clickActive = !isProcessing
            )
            Spacer(modifier = Modifier.height(24.dp))
            ShareCard()
        }
        if (isProcessing) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
private fun RewardsSection(
    rewards: List<Reward>,
    onRewardClick: (Reward) -> Unit,
    clickActive: Boolean,
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        item { HorizontalSpacer(width = 4.dp) }
        items(items = rewards, key = { reward -> reward.id }) { reward ->
            RewardCard(
                reward = reward,
                onRewardClick = { onRewardClick(reward) },
                clickActive = clickActive
            )
        }
        item { HorizontalSpacer(width = 4.dp) }
    }
}

@Composable
private fun GreetingRow(userName: String) {
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
        IconButtonSmall(iconPainter = painterResource(R.drawable.ic_card),
            onClick = { /* not part of task */ })
    }
}

@Composable
private fun PointsSection(
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
                    text = stringResource(R.string.points), style = LocalTypography.current.HeaderL
                )
            }
            Text(text = stringResource(R.string.redeem_points_subtitle))
        }
    }
}

@Composable
private fun ShareCard() {
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

@Composable
private fun LoadingLayout() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun InitializationErrorLayout(
    scrollState: ScrollState,
    onRetryInitialize: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
    ) {
        Text(
            text = stringResource(id = R.string.something_went_wrong),
            style = LocalTypography.current.BodyL,
        )
        VerticalSpacer(height = 16.dp)
        Button(onClick = onRetryInitialize) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}
