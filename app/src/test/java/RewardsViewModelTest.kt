import com.futuremind.loyaltyrewards.common.util.test.TestDispatcherExtension
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetRewards
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetUserPoints
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.SwitchRewardActivationStatus
import com.futuremind.loyaltyrewards.feature.dogs.api.model.Reward
import com.futuremind.loyaltyrewards.view.screens.rewards.RewardsViewModel
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

internal class RewardsViewModelTest {

    companion object {
        private const val userPoints = 2
        private val rewards = listOf(
            Reward(
                coverUrl = "url",
                id = 1,
                name = "name",
                pointsCost = 2,
                state = Reward.State.UNAVAILABLE,
            )
        )
    }

    @RegisterExtension
    private val testDispatcherExtension = TestDispatcherExtension()

    private val getRewardsMock: GetRewards = mockk(relaxed = true)
    private val getUserPointsMock: GetUserPoints = mockk(relaxed = true)
    private val switchRewardActivationStatusMock: SwitchRewardActivationStatus = mockk(relaxed = true)

    private lateinit var systemUnderTest: RewardsViewModel

    @BeforeEach
    fun setUp() {
        systemUnderTest = RewardsViewModel(
            getRewards = getRewardsMock,
            getUserPoints = getUserPointsMock,
            switchRewardActivationStatus = switchRewardActivationStatusMock,
        )
    }

    @Nested
    inner class Initialization {

        @Test
        fun `Should have Loading as initial state`() {
            systemUnderTest.viewState.value shouldBe RewardsViewModel.ViewState.Loading
        }

        @Test
        fun `Should set Error state if getRewards() results with error`() {
            givenGetRewardsReturns { GetRewards.Result.Failure }

            systemUnderTest.initialize()

            systemUnderTest.viewState.value shouldBe RewardsViewModel.ViewState.InitializationError
        }

        @Test
        fun `Should set Error state if getUserPoints results with error`() {
            givenGetUserPointsReturns { GetUserPoints.Result.Failure }

            systemUnderTest.initialize()

            systemUnderTest.viewState.value shouldBe RewardsViewModel.ViewState.InitializationError
        }

        @Test
        fun `Should set DataLoaded state when data successfully fetched`() {
            givenGetRewardsReturns { GetRewards.Result.Success(items = rewards) }
            givenGetUserPointsReturns { GetUserPoints.Result.Success(points = userPoints) }

            systemUnderTest.initialize()

            systemUnderTest.viewState.value shouldBe RewardsViewModel.ViewState.DataLoaded(
                rewards = rewards,
                points = userPoints,
            )
        }
    }

    @Nested
    inner class RetryInitialize {
        // TODO
    }

    @Nested
    inner class RewardClick {
        // TODO
    }

    @Nested
    inner class Refresh {
        // TODO
    }

    private fun givenGetRewardsReturns(
        resultFactory: suspend () -> GetRewards.Result
    ) {
        coEvery { getRewardsMock.invoke() } coAnswers { resultFactory() }
    }

    private fun givenGetUserPointsReturns(
        resultFactory: suspend () -> GetUserPoints.Result
    ) {
        coEvery { getUserPointsMock.invoke() } coAnswers { resultFactory() }
    }
}