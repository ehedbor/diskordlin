package io.github.ehedbor.diskordlin.entities.gateway

import com.google.gson.annotations.SerializedName

data class HelloPayload(
    @SerializedName("heartbeat_interval")
    val heartbeatInterval: Long,
    @SerializedName("_trace")
    val trace: List<String> = emptyList()
)
