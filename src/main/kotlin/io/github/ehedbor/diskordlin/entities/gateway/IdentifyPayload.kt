package io.github.ehedbor.diskordlin.entities.gateway

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

@Serializable
data class IdentifyPayload(
    val token: String,
    val properties: IdentifyProperties,
    val compress: Boolean,
    @Name("large_threshold")  val largeThreshold: Int,
    @Optional val shard: List<Int> = emptyList(),
    val presence: StatusUpdate
)

@Serializable
data class IdentifyProperties(
    @Name("\$os") val os: String,
    @Name("\$browser") val browser: String,
    @Name("\$device")  val device: String
)