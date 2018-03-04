package io.github.ehedbor.diskordlin.entities.channel

import io.github.ehedbor.diskordlin.entities.Snowflake
import io.github.ehedbor.diskordlin.entities.user.User
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

@Serializable
data class Channel(
    val id: Snowflake,
    val type: Int,
    @Optional @Name("guild_id") val guildId: Snowflake? = null,
    @Optional val position: Int? = null,
    @Optional @Name("permission_overwrites") val permissionOverwrites: List<Overwrite> = emptyList(),
    @Optional val name: String? = null,
    @Optional val topic: String? = null,
    @Optional val nsfw: Boolean? = null,
    @Optional @Name("last_message_id") val lastMessageId: Snowflake? = null,
    @Optional val bitrate: Long? = null,
    @Optional @Name("user_limit") val userLimit: Int? = null,
    @Optional val recipients: List<User> = emptyList(),
    @Optional val icon: String? = null,
    @Optional @Name("owner_id") val ownerId: Snowflake? = null,
    @Optional @Name("application_id") val applicationId: Snowflake? = null,
    @Optional  @Name("parent_id") val parentId: Snowflake? = null,
    @Optional @Name("last_pin_timestamp") val lastPinTimestamp: String? = null
)

object ChannelType {
    const val GUILD_TEXT = 0
    const val DM = 1
    const val GUILD_VOICE = 2
    const val GROUP_DM = 3
    const val GUILD_CATEGORY = 4
}