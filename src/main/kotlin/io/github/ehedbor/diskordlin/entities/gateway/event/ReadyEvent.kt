package io.github.ehedbor.diskordlin.entities.gateway.event

import com.google.gson.annotations.SerializedName
import io.github.ehedbor.diskordlin.entities.channel.Channel
import io.github.ehedbor.diskordlin.entities.guild.UnavailableGuild
import io.github.ehedbor.diskordlin.entities.user.User

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