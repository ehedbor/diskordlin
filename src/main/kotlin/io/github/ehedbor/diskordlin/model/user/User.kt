package io.github.ehedbor.diskordlin.model.user

import com.google.gson.annotations.SerializedName
import io.github.ehedbor.diskordlin.model.Snowflake

data class User(
    var id: Snowflake,
    var username: String,
    var discriminator: String,
    var avatar: String? = null,
    var bot: Boolean? = null,
    @SerializedName("mfa_enabled")
    var mfaEnabled: Boolean? = null,
    var verified: Boolean? = null,
    var email: String? = null
)