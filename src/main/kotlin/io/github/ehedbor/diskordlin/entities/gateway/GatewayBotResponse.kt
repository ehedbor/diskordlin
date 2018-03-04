package io.github.ehedbor.diskordlin.entities.gateway

import kotlinx.serialization.Serializable

@Serializable
data class GatewayBotResponse(val url: String, val shards: Int)