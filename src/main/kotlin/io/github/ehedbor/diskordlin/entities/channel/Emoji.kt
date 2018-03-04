package io.github.ehedbor.diskordlin.entities.channel

import io.github.ehedbor.diskordlin.entities.user.Role
import io.github.ehedbor.diskordlin.entities.user.User
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

@Suppress("MemberVisibilityCanPrivate")
@Serializable
data class Emoji(
    @Optional val id: String? = null,
    val name: String,
    @Optional val roles: List<Role> = emptyList(),
    @Optional val user: User? = null,
    @Optional @Name("require_colons") val requireColons: Boolean? = null,
    @Optional val managed: Boolean? = null,
    @Optional val animated: Boolean? = null
)