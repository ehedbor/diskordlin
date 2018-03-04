package io.github.ehedbor.diskordlin.entities.user

import io.github.ehedbor.diskordlin.entities.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

@Serializable
data class Activity(
    val name: String,
    val type: Int,
    @Optional val url: String? = null,
    @Optional val timestamps: Timestamps? = null,
    @Optional @Name("application_id") val applicationId: Snowflake? = null,
    @Optional val details: String? = null,
    @Optional val state: String? = null,
    @Optional val party: Party? = null,
    @Optional val assets: Assets? = null
)

object ActivityType {
    const val GAME = 0
    const val STREAMING = 1
    const val LISTENING = 2
}

@Serializable
data class Timestamps(
    @Optional val start: Long? = null,
    @Optional val end: Long? = null
)

@Serializable
data class Party(
    @Optional val id: Snowflake? = null,
    @Optional val size: List<Int> = listOf()
)

@Serializable
data class Assets(
    @Optional @Name("large_image") val largeImage: String? = null,
    @Optional @Name("large_text")  val largeText: String? = null,
    @Optional @Name("small_image") val smallImage: String? = null,
    @Optional @Name("small_text")  val smallText: String? = null
)