package io.github.ehedbor.diskordlin.entities.gateway

import com.google.gson.annotations.SerializedName

data class IdentifyPayload(
    val token: String,
    val properties: IdentifyProperties,
    val compress: Boolean,
    @SerializedName("large_threshold")
    val largeThreshold: Int,
    val shard: List<Int> = emptyList(),
    val presence: StatusUpdate
)

data class IdentifyProperties(
    @SerializedName("\$os")
    val os: String,
    @SerializedName("\$browser")
    val browser: String,
    @SerializedName("\$device")
    val device: String
)