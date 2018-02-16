package io.github.ehedbor.diskordlin.entities.gateway

import io.github.ehedbor.diskordlin.entities.user.Activity

data class StatusUpdate(
    val since: Long? = null,
    val game: Activity? = null,
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