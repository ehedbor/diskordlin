package io.github.ehedbor.diskordlin.model.user

import io.github.ehedbor.diskordlin.model.Snowflake

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