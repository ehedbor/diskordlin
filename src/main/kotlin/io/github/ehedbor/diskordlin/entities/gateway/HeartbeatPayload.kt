package io.github.ehedbor.diskordlin.entities.gateway

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

@Serializable
data class HeartbeatPayload(
    @Optional @Name("op") val opcode: Int? = null,
    @Optional @Name("d") val lastSequenceNumber: Int? = null
)