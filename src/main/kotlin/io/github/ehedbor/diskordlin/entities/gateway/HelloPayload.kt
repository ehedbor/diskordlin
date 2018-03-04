package io.github.ehedbor.diskordlin.entities.gateway

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

@Serializable
data class HelloPayload(
    @Name("heartbeat_interval") val heartbeatInterval: Long,
    @Optional @Name("_trace") val trace: List<String> = emptyList()
)
