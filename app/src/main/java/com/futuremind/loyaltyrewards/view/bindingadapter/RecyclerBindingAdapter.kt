package com.futuremind.loyaltyrewards.view.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

@BindingAdapter("itemsSource")
fun <T>setupSource(recyclerView: RecyclerView, items: List<T>?) {
    (recyclerView.adapter as ListAdapter<T, out ViewHolder>).submitList(items)
}