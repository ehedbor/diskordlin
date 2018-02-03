package io.github.ehedbor.diskordlin.model.chat

import com.google.gson.annotations.SerializedName
import io.github.ehedbor.diskordlin.model.user.Overwrite
import io.github.ehedbor.diskordlin.model.user.User

data class Channel(
    val id: String,
    val type: Int,
    @SerializedName("guild_id")
    val guildId: String? = null,
    val position: Int? = null,
    @SerializedName("permission_overwrites")
    val permissionOverwrites: List<Overwrite> = emptyList(),
    val name: String? = null,
    val topic: String? = null,
    val nsfw: Boolean? = null,
    @SerializedName("last_message_id")
    val lastMessageId: String? = null,
    val bitrate: Long? = null,
    @SerializedName("user_limit")
    val userLimit: Int? = null,
    val recipients: List<User> = emptyList(),
    val icon: String? = null,
    @SerializedName("owner_id")
    val ownerId: String? = null,
    @SerializedName("application_id")
    val applicationId: String? = null,
    @SerializedName("parent_id")
    val parentId: String? = null,
    @SerializedName("last_pin_timestamp")
    val lastPinTimestamp: String? = null
)

object ChannelType {
    const val GUILD_TEXT = 0
    const val DM = 1
    const val GUILD_VOICE = 2
    const val GROUP_DM = 3
    const val GUILD_CATEGORY = 4
}