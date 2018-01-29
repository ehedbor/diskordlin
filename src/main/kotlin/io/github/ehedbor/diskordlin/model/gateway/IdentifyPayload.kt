package io.github.ehedbor.diskordlin.model.gateway

import com.google.gson.annotations.SerializedName

data class IdentifyPayload(
    val token: String? = null,
    val properties: IdentifyProperties? = null,
    val compress: Boolean? = null,
    @SerializedName("large_threshold")
    val largeThreshold: Int? = null,
    val shard: List<Int> = emptyList(),
    val presence: StatusUpdatePayload? = null
) : IGatewayPayload

data class IdentifyProperties(
    @SerializedName("\$os")
    val os: String? = null,
    @SerializedName("\$browser")
    val browser: String? = null,
    @SerializedName("\$device")
    val device: String? = null
)