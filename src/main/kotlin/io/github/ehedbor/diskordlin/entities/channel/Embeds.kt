package io.github.ehedbor.diskordlin.entities.channel

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

// This is the ideal file body. You may not like it,
// but this is what peak performance looks like.

@Suppress("ArrayInDataClass")
@Serializable
data class Embed(
    @Optional val title: String? = null,
    @Optional val type: String? = null,
    @Optional val description: String? = null,
    @Optional val url: String? = null,
    @Optional val timestamp: String? = null,
    @Optional val color: Int? = null,
    @Optional val footer: EmbedFooter? = null,
    @Optional val image: EmbedImage? = null,
    @Optional val thumbnail: EmbedThumbnail? = null,
    @Optional val video: EmbedVideo? = null,
    @Optional val provider: EmbedProvider? = null,
    @Optional val author: EmbedAuthor? = null,
    @Optional val fields: List<EmbedField> = emptyList()
)

@Serializable
data class EmbedFooter(
    val text: String,
    @Optional @Name("icon_url") val iconUrl: String? = null,
    @Optional @Name("proxy_icon_url")  val proxyIconUrl: String? = null
)

@Serializable
data class EmbedImage(
    val url: String,
    @Optional @Name("proxy_url") val proxyUrl: String? = null,
    @Optional val height: Int? = null,
    @Optional val width: Int? = null
)

@Serializable
data class EmbedThumbnail(
    @Optional val url: String? = null,
    @Optional @Name("proxy_url") val proxyUrl: String? = null,
    @Optional val height: Int? = null,
    @Optional val width: Int? = null
)

@Serializable
data class EmbedVideo(
    @Optional val url: String? = null,
    @Optional val height: Int? = null,
    @Optional val width: Int? = null
)

@Serializable
data class EmbedProvider(
    @Optional val name: String? = null,
    @Optional val url: String? = null
)

@Serializable
data class EmbedAuthor(
    @Optional val name: String? = null,
    @Optional val url: String? = null,
    @Optional @Name("icon_url") val iconUrl: String? = null,
    @Optional @Name("proxy_icon_url") val proxyIconUrl: String? = null
)

@Serializable
data class EmbedField(
    @Optional val name: String? = null,
    @Optional val value: String? = null,
    @Optional val inline: Boolean? = null
)