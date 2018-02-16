package io.github.ehedbor.diskordlin.entities.user

import com.google.gson.annotations.SerializedName
import io.github.ehedbor.diskordlin.entities.Snowflake

data class User(
    var id: Snowflake,
    var username: String,
    var discriminator: String,
    var avatar: String? = null,
    @SerializedName("bot")
    var isBot: Boolean? = null,
    @SerializedName("mfa_enabled")
    var mfaEnabled: Boolean? = null,
    var verified: Boolean? = null,
    var email: String? = null
)