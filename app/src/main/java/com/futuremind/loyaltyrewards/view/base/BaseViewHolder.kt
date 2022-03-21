package com.futuremind.loyaltyrewards.view.base

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T : Any, VB : ViewBinding>(viewBinding: VB) : RecyclerView.ViewHolder(viewBinding.root) {

    protected lateinit var item: T

    protected val resources: Resources = viewBinding.root.resources

    open fun bind(item: T, payloads: List<Any> = emptyList()) {
        this.item = item
        bindView(item, payloads)
    }

    protected abstract fun bindView(item: T, payloads: List<Any> = emptyList())

    protected inline fun <reified T> List<Any>.processChanges(payloadHandler: (T) -> Unit) {
        forEach { payloadList ->
            (payloadList as? List<*>)
                ?.filterIsInstance(T::class.java)
                ?.forEach { change -> payloadHandler(change) }
        }
    }
}
