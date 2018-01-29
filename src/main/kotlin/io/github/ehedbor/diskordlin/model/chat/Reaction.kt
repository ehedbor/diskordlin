package io.github.ehedbor.diskordlin.model.chat

data class Reaction(         
    val count: Int,
    val me: Boolean,
    val emoji: Emoji
)