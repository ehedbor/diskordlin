package io.github.ehedbor.diskordlin.models

import com.beust.klaxon.Json

data class User(
    var id: String? = null,
    var username: String? = null,
    var discriminator: String? = null,
    var avatar: String? = null,
    var bot: Boolean? = null,
    @Json(name = "mfa_enabled") var mfaEnabled: Boolean? = null,
    var verified: Boolean? = null,
    var email: String? = null
)