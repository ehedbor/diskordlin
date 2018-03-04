package io.github.ehedbor.diskordlin.entities.channel

import io.github.ehedbor.diskordlin.entities.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

@Serializable
data class Attachment(
    @Optional val id: Snowflake? = null,
    @Optional @Name("filename") val fileName: String? = null,
    @Optional val size: Int? = null,
    @Optional val url: String? = null,
    @Optional @Name("filename") val proxyUrl: String? = null,
    @Optional val height: Int? = null,
    @Optional val width: Int? = null
)