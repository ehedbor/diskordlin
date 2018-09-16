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
import io.github.ehedbor.diskordlin.entities.channel.Channel
import io.github.ehedbor.diskordlin.entities.channel.Emoji
import io.github.ehedbor.diskordlin.entities.gateway.event.PresenceUpdateEvent
import io.github.ehedbor.diskordlin.entities.user.Role

// Not even Kotlin can save me now

/**
 * Represents an isolated collection of users and channels.
 * Often referred to as "servers" in the Discord UI.
 *
 * Documentation copied from [here](https://discordapp.com/developers/docs/resources/guild#member-object).
 *
 * @property id guild id
 * @property name guild name (2-100 characters)
 * @property icon icon hash
 * @property splash splash hash
 * @property owner whether or not the user is the owner of the guild
 * @property ownerId id of owner
 * @property permissions total permissions for the user in the guild (does not include channel overrides)
 * @property region voice region id for the guild
 * @property afkChannelId id of afk channel
 * @property afkTimeout afk timeout in seconds
 * @property embedEnabled is this guild embeddable (e.g. widget)
 * @property embedChannelId if not null, the channel id that the widget will generate an invite to
 * @property verificationLevel verification level required for the guild
 * @property defaultMessageNotifications default message notifications level
 * @property explicitContentFilter explicit content filter level
 * @property roles roles in the guild
 * @property emojis custom guild emojis
 * @property features enabled guild features
 * @property mfaLevel required MFA level for the guild
 * @property applicationId application id of the guild creator if it is bot-created
 * @property widgetEnabled whether or not the server widget is enabled
 * @property widgetChannelId the channel id for the server widget
 * @property systemChannelId the id of the channel to which system messages are sent
 * @property joinedAt when this guild was joined at (ISO8601 timestamp)
 * @property large whether this is considered a large guild
 * @property unavailable is this guild unavailable
 * @property memberCount total number of members in this guild
 * @property voiceStates partial voice state objects (without the guild_id key)
 * @property members users in the guild
 * @property channels channels in the guild
 * @property presences presences of the users in the guild
 */
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
    @Json("member_count") val memberCount: Int? = null,
    @Json("voice_states") val voiceStates: List<VoiceState> = emptyList(),
    val members: List<GuildMember> = emptyList(),
    val channels: List<Channel> = emptyList(),
    val presences: List<PresenceUpdateEvent> = emptyList()
)