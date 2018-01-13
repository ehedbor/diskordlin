package io.github.ehedbor.diskordlin.models

import com.beust.klaxon.Json

// This is the ideal file body. You may not like it,
// but this is what peak performance looks like.

@Suppress("ArrayInDataClass")
data class Embed(
    var title: String? = null,
    var type: String? = null,
    var description: String? = null,
    var url: String? = null,
    var timestamp: String? = null,
    var color: Int? = null,
    var footer: EmbedFooter? = null,
    var image: EmbedImage? = null,
    var thumbnail: EmbedThumbnail? = null,
    var video: EmbedVideo? = null,
    var provider: EmbedProvider? = null,
    var author: EmbedAuthor? = null,
    var fields: List<EmbedField> = emptyList()
)

data class EmbedFooter(
    var text: String,
    @Json(name = "icon_url") var iconUrl: String? = null,
    @Json(name = "proxy_icon_url") var proxyIconUrl: String? = null
)

data class EmbedImage(
    var url: String,
    @Json(name = "proxy_url") var proxyUrl: String? = null,
    var height: Int? = null,
    var width: Int? = null
)

data class EmbedThumbnail(
    var url: String? = null,
    @Json(name = "proxy_url") var proxyUrl: String? = null,
    var height: Int? = null,
    var width: Int? = null
)

data class EmbedVideo(
    var url: String? = null,
    var height: Int? = null,
    var width: Int? = null
)

data class EmbedProvider(
    var name: String? = null,
    var url: String? = null
)

data class EmbedAuthor(
    var name: String? = null,
    var url: String? = null,
    @Json(name = "icon_url") var iconUrl: String? = null,
    @Json(name = "proxy_icon_url") var proxyIconUrl: String? = null
)

data class EmbedField(
    var name: String? = null,
    var value: String? = null,
    var inline: Boolean? = null
)