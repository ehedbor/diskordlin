package io.github.ehedbor.diskordlin.model.gateway.event

import com.google.gson.annotations.SerializedName
import io.github.ehedbor.diskordlin.model.chat.Channel
import io.github.ehedbor.diskordlin.model.chat.UnavailableGuild
import io.github.ehedbor.diskordlin.model.user.User

data class ReadyEvent(
    @SerializedName("v")
    val protocolVersion: Int,
    val user: User,
    @SerializedName("private_channels")
    val privateChannels: List<Channel>,
    val guilds: List<UnavailableGuild>,
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("_trace")
    val trace: List<String>
)