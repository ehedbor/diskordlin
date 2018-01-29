package io.github.ehedbor.diskordlin.model.gateway

import com.google.gson.annotations.SerializedName

data class HeartbeatPayload(
    @SerializedName("op")
    val opcode: Int? = null,
    @SerializedName("d")
    val lastSequenceNumber: Int? = null
) : IGatewayPayload