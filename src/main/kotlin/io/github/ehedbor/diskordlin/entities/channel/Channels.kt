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

import com.beust.klaxon.Json
import io.github.ehedbor.diskordlin.entities.Snowflake
import io.github.ehedbor.diskordlin.entities.user.User

data class Channel(
    val id: Snowflake,
    val type: Int,
    @Json("guild_id") val guildId: Snowflake? = null,
    val position: Int? = null,
    @Json("permission_overwrites") val permissionOverwrites: List<Overwrite> = emptyList(),
    val name: String? = null,
    val topic: String? = null,
    val nsfw: Boolean? = null,
    @Json("last_message_id") val lastMessageId: Snowflake? = null,
    val bitrate: Long? = null,
    @Json("user_limit") val userLimit: Int? = null,
    val recipients: List<User> = emptyList(),
    val icon: String? = null,
    @Json("owner_id") val ownerId: Snowflake? = null,
    @Json("application_id") val applicationId: Snowflake? = null,
    @Json("parent_id") val parentId: Snowflake? = null,
    @Json("last_pin_timestamp") val lastPinTimestamp: String? = null
)

object ChannelType {
    const val GUILD_TEXT = 0
    const val DM = 1
    const val GUILD_VOICE = 2
    const val GROUP_DM = 3
    const val GUILD_CATEGORY = 4
}