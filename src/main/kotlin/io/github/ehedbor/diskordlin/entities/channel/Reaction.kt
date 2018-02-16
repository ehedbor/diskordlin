package io.github.ehedbor.diskordlin.entities.channel

data class Reaction(
    val count: Int,
    val me: Boolean,
    val emoji: Emoji
)