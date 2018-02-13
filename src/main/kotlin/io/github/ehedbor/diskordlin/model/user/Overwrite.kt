package io.github.ehedbor.diskordlin.model.user

import io.github.ehedbor.diskordlin.model.Snowflake

data class Overwrite(
    val id: Snowflake,
    val type: String,
    val allow: Long,
    val deny: Long
)