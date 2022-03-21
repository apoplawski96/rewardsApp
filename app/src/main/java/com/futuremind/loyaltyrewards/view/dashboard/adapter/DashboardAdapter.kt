package com.futuremind.loyaltyrewards.view.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.databinding.ItemCounterLoopBinding
import com.futuremind.loyaltyrewards.databinding.ItemSectionTitleBinding
import com.futuremind.loyaltyrewards.databinding.ItemShareBannerBinding
import com.futuremind.loyaltyrewards.domain.dashboard.DashboardItem
import com.futuremind.loyaltyrewards.domain.dashboard.DashboardItem.Companion.COUNTER_LOOP_VIEW_TYPE
import com.futuremind.loyaltyrewards.domain.dashboard.DashboardItem.Companion.SECTION_TITLE_VIEW_TYPE
import com.futuremind.loyaltyrewards.domain.dashboard.DashboardItem.Companion.SHARE_BANNER_VIEW_TYPE
import com.futuremind.loyaltyrewards.domain.dashboard.DashboardItem.CounterLoop
import com.futuremind.loyaltyrewards.domain.dashboard.DashboardItem.SectionTitle
import com.futuremind.loyaltyrewards.view.base.BaseViewHolder
import com.futuremind.loyaltyrewards.view.dashboard.adapter.payload.CounterLoopPayload
import com.futuremind.loyaltyrewards.view.dashboard.adapter.payload.CounterLoopPayload.POINTS_CHANGE
import com.futuremind.loyaltyrewards.view.dashboard.adapter.payload.DashboardPayload

class DashboardAdapter : ListAdapter<DashboardItem, BaseViewHolder<DashboardItem, out ViewBinding>>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DashboardItem, out ViewBinding> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            SECTION_TITLE_VIEW_TYPE -> {
                val itemView = ItemSectionTitleBinding.inflate(inflater, parent, false)
                SectionTitleViewHolder(itemView)
            }
            COUNTER_LOOP_VIEW_TYPE -> {
                val itemView = ItemCounterLoopBinding.inflate(inflater, parent, false)
                CounterLoopViewHolder(itemView)
            }
            SHARE_BANNER_VIEW_TYPE -> {
                val itemView = ItemShareBannerBinding.inflate(inflater, parent, false)
                ShareBannerViewHolder(itemView)
            }
            else -> throw IllegalStateException("Unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DashboardItem, out ViewBinding>, position: Int) =
        onBindViewHolder(holder, position, emptyList())

    override fun onBindViewHolder(
        holder: BaseViewHolder<DashboardItem, out ViewBinding>, position: Int, payloads: List<Any>
    ) = holder.bind(getItem(position), payloads)

    override fun getItemViewType(position: Int): Int = getItem(position).viewType
}

private val DIFF_CALLBACK: DiffUtil.ItemCallback<DashboardItem> = object : DiffUtil.ItemCallback<DashboardItem>() {

    override fun areItemsTheSame(oldItem: DashboardItem, newItem: DashboardItem): Boolean =
        oldItem::class.java == newItem::class.java

    override fun areContentsTheSame(oldItem: DashboardItem, newItem: DashboardItem): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: DashboardItem, newItem: DashboardItem) = mutableListOf<DashboardPayload>().apply {
        if (oldItem is CounterLoop && newItem is CounterLoop) {
            if (newItem.points != oldItem.points) add(POINTS_CHANGE)
        }
    }
}

private class SectionTitleViewHolder(
    private val viewBinding: ItemSectionTitleBinding,
) : BaseViewHolder<DashboardItem, ItemSectionTitleBinding>(viewBinding) {

    override fun bindView(item: DashboardItem, payloads: List<Any>) {
        if (item !is SectionTitle) return
        viewBinding.title = itemView.context.getString(R.string.hi_user, item.user)
    }
}

private class CounterLoopViewHolder(
    private val viewBinding: ItemCounterLoopBinding,
) : BaseViewHolder<DashboardItem, ItemCounterLoopBinding>(viewBinding) {

    override fun bindView(item: DashboardItem, payloads: List<Any>) {
        if (item !is CounterLoop) return

        with(viewBinding) {
            if (payloads.isEmpty()) {
                points = item.points
            } else {
                payloads.processChanges { payload: CounterLoopPayload ->
                    points = item.points
                }
            }
            executePendingBindings()
        }
    }
}

private class ShareBannerViewHolder(
    viewBinding: ItemShareBannerBinding,
) : BaseViewHolder<DashboardItem, ItemShareBannerBinding>(viewBinding) {

    override fun bindView(item: DashboardItem, payloads: List<Any>) = Unit
}
