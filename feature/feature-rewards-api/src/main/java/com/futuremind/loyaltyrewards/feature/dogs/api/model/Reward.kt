package com.futuremind.loyaltyrewards.feature.dogs.api.model

data class Reward(
    val id: Int,
    val name: String,
    val coverUrl: String,
    val pointsCost: Int,
    val state: State,
) {
    enum class State { AVAILABLE, UNAVAILABLE, ACTIVATED; }
}