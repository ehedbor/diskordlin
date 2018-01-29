package io.github.ehedbor.diskordlin.model.gateway

import io.github.ehedbor.diskordlin.model.user.Activity

data class StatusUpdatePayload(
    val since: Long? = null,
    val game: Activity? = null,
    val status: String? = null,
    val afk: Boolean? = null
): IGatewayPayload