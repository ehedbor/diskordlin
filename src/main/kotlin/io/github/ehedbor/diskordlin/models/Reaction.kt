package io.github.ehedbor.diskordlin.models

data class Reaction(         
    var count: Int? = null,
    var me: Boolean? = null,
    var emoji: Emoji? = null
)