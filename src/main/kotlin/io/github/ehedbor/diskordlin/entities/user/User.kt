package io.github.ehedbor.diskordlin.entities.user

import io.github.ehedbor.diskordlin.entities.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

@Serializable
data class User(
    var id: Snowflake,
    var username: String,
    var discriminator: String,
    @Optional var avatar: String? = null,
    @Optional @Name("bot") var isBot: Boolean? = null,
    @Optional @Name("mfa_enabled") var mfaEnabled: Boolean? = null,
    @Optional var verified: Boolean? = null,
    @Optional var email: String? = null
) {

    override fun toString() = username
}