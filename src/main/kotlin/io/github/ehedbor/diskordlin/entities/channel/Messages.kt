package io.github.ehedbor.diskordlin.entities.channel

import io.github.ehedbor.diskordlin.entities.Snowflake
import io.github.ehedbor.diskordlin.entities.user.Role
import io.github.ehedbor.diskordlin.entities.user.User
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

/**
 * A message sent over the Discord API.
 *
 * @param id The message 
 */
@Suppress("MemberVisibilityCanPrivate")
@Serializable
data class Message(
    val id: Snowflake,
    @Name("channel_id") val channelId: Snowflake,
    val author: User,
    val content: String,
    val timestamp: String,
    @Optional @Name("edited_timestamp") val editedTimestamp: String? = null,
    @Optional val tts: Boolean = false,
    @Optional @Name("mention_everyone") val mentionEveryone: Boolean = false,
    @Optional val mentions: List<User> = emptyList(),
    @Optional @Name("mention_roles") val mentionRoles: List<Role> = emptyList(),
    @Optional val attachments: List<Attachment> = emptyList(),
    @Optional val embeds: List<Embed> = emptyList(),
    @Optional val reactions: List<Reaction> = emptyList(),
    @Optional val nonce: Snowflake? = null,
    @Optional val pinned: Boolean = false,
    @Optional @Name("webhook_id") val webhookId: Snowflake? = null,
    val type: Int
)

@Suppress("unused")
object MessageType {
    const val DEFAULT = 0
    const val RECIPIENT_ADD = 1
    const val RECIPIENT_REMOVE = 2
    const val CALL = 3
    const val CHANNEL_NAME_CHANGE = 4
    const val CHANNEL_ICON_CHANGE = 5
    const val CHANNEL_PINNED_MESSAGE = 6
    const val GUILD_MEMBER_JOIN = 7
}