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