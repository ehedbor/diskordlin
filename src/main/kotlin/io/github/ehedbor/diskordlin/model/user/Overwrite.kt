package io.github.ehedbor.diskordlin.model.user

data class Overwrite(
    val id: String,
    val type: String,
    val allow: Long,
    val deny: Long
)