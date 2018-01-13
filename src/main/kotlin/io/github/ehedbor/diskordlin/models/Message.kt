package io.github.ehedbor.diskordlin.models

import com.beust.klaxon.Json

@Suppress("MemberVisibilityCanPrivate")
data class Message(
    var id: String? = null,
    @Json(name = "channel_id") var channelId: String? = null,
    var author: User? = null,
    var content: String? = null,
    var timestamp: String? = null,
    @Json(name = "edited_timestamp") var editedTimestamp: Any? = null,
    var tts: Boolean? = null,
    @Json(name = "mention_everyone") var mentionEveryone: Boolean = false,
    var mentions: List<User> = emptyList(),
    @Json(name = "mention_roles") var mentionRoles: List<Role> = emptyList(),
    var attachments: List<Attachment> = emptyList(),
    var embeds: List<Embed> = emptyList(),
    var reactions: List<Reaction> = emptyList(),
    var pinned: Boolean? = null,
    @Json(name = "webhook_id") var webhookId: String? = null,
    var type: Int? = null
)
