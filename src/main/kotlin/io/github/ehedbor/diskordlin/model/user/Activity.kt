package io.github.ehedbor.diskordlin.model.user

import com.google.gson.annotations.SerializedName

data class Activity(
    val name: String? = null,
    val type: Int? = null,
    val url: String? = null,
    val timestamps: Timestamps? = null,
    @SerializedName("application_id")
    val applicationId: String? = null,
    val details: String? = null,
    val state: String? = null,
    val party: Party? = null,
    val assets: Assets? = null
)

data class Timestamps(val start: Long? = null, val end: Long? = null)

data class Party(val id: String? = null, val size: List<Int> = listOf())

data class Assets(
    @SerializedName("large_image")
    val largeImage: String? = null,
    @SerializedName("large_text")
    val largeText: String? = null,
    @SerializedName("small_image")
    val smallImage: String? = null,
    @SerializedName("small_text")
    val smallText: String? = null
)