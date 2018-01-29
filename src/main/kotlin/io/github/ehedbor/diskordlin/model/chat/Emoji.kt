package io.github.ehedbor.diskordlin.model.chat

import com.google.gson.annotations.SerializedName
import io.github.ehedbor.diskordlin.model.user.Role
import io.github.ehedbor.diskordlin.model.user.User

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