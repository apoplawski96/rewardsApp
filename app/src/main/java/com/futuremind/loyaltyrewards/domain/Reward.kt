package com.futuremind.loyaltyrewards.domain

data class Reward(
    val id: Int,
    val name: String,
    val coverUrl: String,
    val pointsCost: Int,
    val activated: Boolean,
)