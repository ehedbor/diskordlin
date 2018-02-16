package io.github.ehedbor.diskordlin.entities.channel

import com.google.gson.annotations.SerializedName
import io.github.ehedbor.diskordlin.entities.user.Role
import io.github.ehedbor.diskordlin.entities.user.User

@Suppress("MemberVisibilityCanPrivate")
data class Emoji(
    val id: String? = null,
    val name: String,
    val roles: List<Role> = emptyList(),
    val user: User? = null,
    @SerializedName("require_colons")
    val requireColons: Boolean? = null,
    val managed: Boolean? = null,
    val animated: Boolean? = null
)