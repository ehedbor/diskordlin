/*
 * MIT License
 *
 * Copyright (c) 2017-2018 Evan Hedbor
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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