package io.github.ehedbor.diskordlin.entities.channel

import io.github.ehedbor.diskordlin.entities.Snowflake
import kotlinx.serialization.Serializable

@Serializable
data class Overwrite(
    val id: Snowflake,
    val type: String,
    val allow: Long,
    val deny: Long
)