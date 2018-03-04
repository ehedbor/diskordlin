package io.github.ehedbor.diskordlin.entities.channel

import kotlinx.serialization.Serializable

@Serializable
data class Reaction(
    val count: Int,
    val me: Boolean,
    val emoji: Emoji
)