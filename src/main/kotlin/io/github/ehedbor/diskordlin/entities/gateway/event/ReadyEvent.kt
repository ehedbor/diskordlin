package io.github.ehedbor.diskordlin.entities.gateway.event

import io.github.ehedbor.diskordlin.entities.channel.Channel
import io.github.ehedbor.diskordlin.entities.guild.UnavailableGuild
import io.github.ehedbor.diskordlin.entities.user.User
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

@Serializable
data class ReadyEvent(
    @Name("v") val protocolVersion: Int,
    val user: User,
    @Optional @Name("private_channels") val privateChannels: List<Channel> = emptyList(),
    @Optional val guilds: List<UnavailableGuild> = emptyList(),
    @Name("session_id") val sessionId: String,
    @Optional @Name("_trace")  val trace: List<String> = emptyList()
)