package com.futuremind.loyaltyrewards.domain.dashboard

abstract class DashboardItem(val viewType: Int) {

    data class SectionTitle(val user: String) : DashboardItem(SECTION_TITLE_VIEW_TYPE)

    data class CounterLoop(val points: Int) : DashboardItem(COUNTER_LOOP_VIEW_TYPE)

    object ShareBanner : DashboardItem(SHARE_BANNER_VIEW_TYPE)

    companion object {
        const val SECTION_TITLE_VIEW_TYPE = 1
        const val COUNTER_LOOP_VIEW_TYPE = 2
        const val SHARE_BANNER_VIEW_TYPE = 4
    }
}
