package io.github.ehedbor.diskordlin.entities.user

import io.github.ehedbor.diskordlin.entities.Snowflake
import kotlinx.serialization.Serializable

@Serializable
data class Role(
    var id: Snowflake,
    var name: String,
    var color: Int,
    var hoist: Boolean,
    var position: Int,
    var permissions: Int,
    var managed: Boolean,
    var mentionable: Boolean
)