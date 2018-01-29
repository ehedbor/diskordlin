package io.github.ehedbor.diskordlin.model.chat

import com.google.gson.annotations.SerializedName
import io.github.ehedbor.diskordlin.model.user.Role
import io.github.ehedbor.diskordlin.model.user.User

@Suppress("MemberVisibilityCanPrivate")
data class Message(
    val id: String,
    @SerializedName("channel_id")
    val channelId: String,
    val author: User,
    val content: String,
    val timestamp: String,
    @SerializedName("edited_timestamp")
    val editedTimestamp: Any? = null,
    val tts: Boolean,
    @SerializedName("mention_everyone")
    val mentionEveryone: Boolean,
    val mentions: List<User> = emptyList(),
    @SerializedName("mention_roles")
    val mentionRoles: List<Role> = emptyList(),
    val attachments: List<Attachment> = emptyList(),
    val embeds: List<Embed> = emptyList(),
    val reactions: List<Reaction> = emptyList(),
    val nonce: String? = null,
    val pinned: Boolean,
    @SerializedName("webhook_id")
    val webhookId: String? = null,
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