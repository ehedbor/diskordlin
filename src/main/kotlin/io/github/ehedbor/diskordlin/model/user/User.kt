package io.github.ehedbor.diskordlin.model.user

import com.google.gson.annotations.SerializedName

data class User(
    var id: String,
    var username: String,
    var discriminator: String,
    var avatar: String? = null,
    var bot: Boolean? = null,
    @SerializedName("mfa_enabled")
    var mfaEnabled: Boolean? = null,
    var verified: Boolean? = null,
    var email: String? = null
)