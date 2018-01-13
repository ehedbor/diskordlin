package io.github.ehedbor.diskordlin.models

import com.beust.klaxon.Json

data class Attachment(
    var id: String? = null,
    @Json(name = "filename") var fileName: String? = null,
    var size: Int? = null,
    var url: String? = null,
    @Json(name = "proxy_url") var proxyUrl: String? = null,
    var height: Int? = null,
    var width: Int? = null
)