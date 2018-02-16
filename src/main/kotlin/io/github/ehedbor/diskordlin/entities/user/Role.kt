package io.github.ehedbor.diskordlin.entities.user

import io.github.ehedbor.diskordlin.entities.Snowflake

data class Role(
    var id: Snowflake? = null,
    var name: String? = null,
    var color: Int? = null,
    var hoist: Boolean? = null,
    var position: Int? = null,
    var permissions: Int? = null,
    var managed: Boolean? = null,
    var mentionable: Boolean? = null
)