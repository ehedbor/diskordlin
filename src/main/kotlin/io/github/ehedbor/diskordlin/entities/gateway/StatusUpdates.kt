package io.github.ehedbor.diskordlin.entities.gateway

import io.github.ehedbor.diskordlin.entities.user.Activity
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class StatusUpdate(
    @Optional val since: Long? = null,
    @Optional val game: Activity? = null,
    val status: String,
    val afk: Boolean
)

object StatusType {
    const val ONLINE = "online"
    const val DO_NOT_DISTURB = "dnd"
    const val IDLE = "afk"
    const val INVISIBLE = "invisible"
    const val OFFLINE = "offline"
}