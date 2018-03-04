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

package io.github.ehedbor.diskordlin.entities.guild

import io.github.ehedbor.diskordlin.entities.Snowflake
import io.github.ehedbor.diskordlin.entities.channel.Channel
import io.github.ehedbor.diskordlin.entities.channel.Emoji
import io.github.ehedbor.diskordlin.entities.user.Role
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

// Not even Kotlin can save me now

@Serializable
data class Guild(
    val id: Snowflake,
    val name: String,

    @Optional
    val icon: String? = null,
    @Optional
    val splash: String? = null,
    @Optional
    val owner: Boolean? = null,
    @Name("owner_id")
    val ownerId: Snowflake,
    @Optional
    val permissions: Int? = null,
    val region: String,
    @Name("afk_channel_id")
    val afkChannelId: Snowflake,
    @Name("afk_timeout")
    val afkTimeout: Int,
    @Optional @Name("embed_enabled")
    val embedEnabled: Boolean? = null,
    @Name("embed_channel_id")
    val embedChannelId: Snowflake,
    @Name("verification_level")
    val verificationLevel: Int,
    @Name("default_message_notifications")
    val defaultMessageNotifications: Int,
    @Name("explicit_content_filter")
    val explicitContentFilter: Int,
    @Optional
    val roles: List<Role> = emptyList(),
    @Optional
    val emojis: List<Emoji> = emptyList(),
    @Optional
    val features: List<String> = emptyList(),
    @Name("mfa_level")
    val mfaLevel: Int,
    @Optional @Name("application_id")
    val applicationId: Snowflake? = null,
    @Optional @Name("widget_enabled")
    val widgetEnabled: Boolean? = null,
    @Optional @Name("widget_channel_id")
    val widgetChannelId: Snowflake? = null,
    @Optional @Name("system_channel_id")
    val systemChannelId: Snowflake? = null,
    @Optional @Name("joined_at")
    val joinedAt: String? = null,
    @Optional @Name("large")
    val isLarge: Boolean? = null,
    @Optional
    val unavailable: Boolean? = null,
    @Optional @Name("member_count")
    val memberCount: Int? = null,

    // TODO: Implement these classes
    //@Optional @Name("voice_states") val voiceStates: List<VoiceState> = emptyList(),
    //@Optional val members: List<GuildMember> = emptyList(),
    @Optional val channels: List<Channel> = emptyList()
    //@Optional val presences: List<PresenceUpdate> = emptyList()
) {

    override fun toString() = name
}