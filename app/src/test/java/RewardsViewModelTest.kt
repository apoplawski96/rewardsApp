import com.futuremind.loyaltyrewards.common.util.test.TestDispatcherExtension
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetRewards
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.GetUserPoints
import com.futuremind.loyaltyrewards.feature.dogs.api.domain.SwitchRewardActivationStatus
import com.futuremind.loyaltyrewards.view.screens.rewards.RewardsViewModel
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

internal class RewardsViewModelTest {

    @RegisterExtension
    private val testDispatcherExtension = TestDispatcherExtension()

    private val getRewardsMock: GetRewards = mockk()
    private val getUserPointsMock: GetUserPoints = mockk()
    private val switchRewardActivationStatusMock: SwitchRewardActivationStatus = mockk()

    private lateinit var systemUnderTest: RewardsViewModel

    @BeforeEach
    fun setUp() {
        systemUnderTest = RewardsViewModel(
            getRewards = getRewardsMock,
            getUserPoints = getUserPointsMock,
            switchRewardActivationStatus = switchRewardActivationStatusMock,
        )
    }

    @Test
    fun siema() {
        systemUnderTest.initialize()
    }
}