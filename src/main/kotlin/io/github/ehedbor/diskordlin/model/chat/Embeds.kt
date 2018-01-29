package io.github.ehedbor.diskordlin.model.chat

import com.google.gson.annotations.SerializedName

// This is the ideal file body. You may not like it,
// but this is what peak performance looks like.

@Suppress("ArrayInDataClass")
data class Embed(
    val title: String? = null,
    val type: String? = null,
    val description: String? = null,
    val url: String? = null,
    val timestamp: String? = null,
    val color: Int? = null,
    val footer: EmbedFooter? = null,
    val image: EmbedImage? = null,
    val thumbnail: EmbedThumbnail? = null,
    val video: EmbedVideo? = null,
    val provider: EmbedProvider? = null,
    val author: EmbedAuthor? = null,
    val fields: List<EmbedField> = emptyList()
)

data class EmbedFooter(
    val text: String,
    @SerializedName("icon_url")
    val iconUrl: String? = null,
    @SerializedName("proxy_icon_url")
    val proxyIconUrl: String? = null
)

data class EmbedImage(
    val url: String,
    @SerializedName("proxy_url")
    val proxyUrl: String? = null,
    val height: Int? = null,
    val width: Int? = null
)

data class EmbedThumbnail(
    val url: String? = null,
    @SerializedName("proxy_url")
    val proxyUrl: String? = null,
    val height: Int? = null,
    val width: Int? = null
)

data class EmbedVideo(
    val url: String? = null,
    val height: Int? = null,
    val width: Int? = null
)

data class EmbedProvider(
    val name: String? = null,
    val url: String? = null
)

data class EmbedAuthor(
    val name: String? = null,
    val url: String? = null,
    @SerializedName("icon_url")
    val iconUrl: String? = null,
    @SerializedName("proxy_icon_url")
    val proxyIconUrl: String? = null
)

data class EmbedField(
    val name: String? = null,
    val value: String? = null,
    val inline: Boolean? = null
)