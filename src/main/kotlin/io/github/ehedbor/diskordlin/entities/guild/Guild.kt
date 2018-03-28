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

import com.beust.klaxon.Json
import io.github.ehedbor.diskordlin.entities.Snowflake
import io.github.ehedbor.diskordlin.entities.channel.Emoji
import io.github.ehedbor.diskordlin.entities.user.Role

// Not even Kotlin can save me now

data class Guild(
    val id: Snowflake,
    val name: String,
    val icon: String? = null,
    val splash: String? = null,
    val owner: Boolean? = null,
    @Json("owner_id") val ownerId: Snowflake,
    val permissions: Int? = null,
    val region: String,
    @Json("afk_channel_id") val afkChannelId: Snowflake,
    @Json("afk_timeout") val afkTimeout: Int,
    @Json("embed_enabled") val embedEnabled: Boolean? = null,
    @Json("embed_channel_id") val embedChannelId: Snowflake,
    @Json("verification_level") val verificationLevel: Int,
    @Json("default_message_notifications") val defaultMessageNotifications: Int,
    @Json("explicit_content_filter") val explicitContentFilter: Int,
    val roles: List<Role> = emptyList(),
    val emojis: List<Emoji> = emptyList(),
    val features: List<String> = emptyList(),
    @Json("mfa_level") val mfaLevel: Int,
    @Json("application_id") val applicationId: Snowflake? = null,
    @Json("widget_enabled") val widgetEnabled: Boolean? = null,
    @Json("widget_channel_id") val widgetChannelId: Snowflake? = null,
    @Json("system_channel_id") val systemChannelId: Snowflake? = null,
    @Json("joined_at") val joinedAt: String? = null,
    @Json("large") val isLarge: Boolean? = null,
    val unavailable: Boolean? = null, 
    @Json("member_count") val memberCount: Int? = null//,

    // TODO: Implement these classes
    //@Json("voice_states") val voiceStates: List<VoiceState> = emptyList(),
    //val members: List<GuildMember> = emptyList(),
    //val channels: List<Channel> = emptyList(),
    //val presences: List<PresenceUpdate> = emptyList()
)