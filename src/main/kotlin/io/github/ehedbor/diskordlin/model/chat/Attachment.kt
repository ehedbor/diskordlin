package io.github.ehedbor.diskordlin.model.chat

import com.google.gson.annotations.SerializedName

data class Attachment(
    val id: String? = null,
    @SerializedName("filename")
    val fileName: String? = null,
    val size: Int? = null,
    val url: String? = null,
    @SerializedName("proxy_url")
    val proxyUrl: String? = null,
    val height: Int? = null,
    val width: Int? = null
)