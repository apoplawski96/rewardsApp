package com.futuremind.loyaltyrewards.view.screens.rewards

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.futuremind.loyaltyrewards.common.ui.model.LoadingState
import com.futuremind.loyaltyrewards.common.ui.theme.LocalColors
import com.futuremind.loyaltyrewards.common.ui.theme.LocalTypography
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward
import com.futuremind.loyaltyrewards.view.screens.rewards.components.RewardCard
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.getViewModel


@Composable
fun RewardsLayout(viewModel: RewardsViewModel = getViewModel()) {

    val errorSnackbarState = remember { SnackbarHostState() }

    LaunchedEffect(null) {
        viewModel.initialize()
        viewModel.viewEvent.collect { event ->
            when (event) {
                RewardsViewModel.ViewEvent.Error -> {
                    errorSnackbarState.showSnackbar("error kurwa")
                }
            }
        }
    }

    val viewState by viewModel.viewState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()


    RewardsLayoutContent(
        onRefresh = { viewModel.refresh() },
        viewState = viewState,
        isLoading = isLoading,
        onRewardClick = { reward ->
            executeIfNotCurrentlyProcessing(state = viewState) {
                viewModel.onRewardClick(reward)
            }
        },
        errorSnackbarState = errorSnackbarState,
    )
}

@Composable
private fun RewardsLayoutContent(
    onRefresh: () -> Unit,
    viewState: RewardsViewModel.ViewState,
    isLoading: Boolean,
    onRewardClick: (Reward) -> Unit,
    errorSnackbarState: SnackbarHostState,
) {


    val scrollState = rememberScrollState()

    Box {
        Column {
            TopBar(
                title = stringResource(R.string.rewards),
                onBack = { /* not part of task */ }
            )
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isLoading),
                onRefresh = onRefresh,
            ) {
                when (viewState) {
                    is RewardsViewModel.ViewState.DataLoaded -> {
                        println("2137 - VIEW STATE: ${viewState.rewards}")

                        Column {
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
                                    onRewardClick = onRewardClick
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                ShareCard()
                            }
                        }
                    }
                    is RewardsViewModel.ViewState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
//                    RewardsViewModel.ViewState.Error -> {
//                        Button(onClick = { onRefresh() }) {
//                            Text(text = "Retry")
//                        }
//                    }
                    RewardsViewModel.ViewState.InitializationError -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Button(onClick = { onRefresh() }, modifier = Modifier.align(Alignment.Center)) {
                                Text(text = "Retry")
                            }
                        }
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

// TODO: Add keys
@Composable
private fun RewardsSection(
    rewards: List<Reward>,
    onRewardClick: (Reward) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { HorizontalSpacer(width = 4.dp) }
        items(rewards) { reward ->
            RewardCard(
                reward = reward,
                onRewardClick = {
                    onRewardClick(reward)
                }
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
        IconButtonSmall(
            iconPainter = painterResource(R.drawable.ic_card),
            onClick = { /* not part of task */ }
        )
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

private fun executeIfNotCurrentlyProcessing(
    state: RewardsViewModel.ViewState,
    block: () -> Unit
) {
    if (state !is LoadingState) {
        block()
    }
}
