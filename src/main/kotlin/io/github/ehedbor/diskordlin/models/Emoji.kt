package io.github.ehedbor.diskordlin.models

import com.beust.klaxon.Json

@Suppress("MemberVisibilityCanPrivate")
data class Emoji(
    var id: String? = null,
    var name: String? = null,
    var roles: List<Role> = emptyList(),
    var user: User? = null,
    @Json(name = "require_colons") var requireColons: Boolean? = null,
    var managed: Boolean? = null,
    var animated: Boolean? = null
)