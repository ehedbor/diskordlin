package io.github.ehedbor.diskordlin.entities.guild

import com.beust.klaxon.Json
import io.github.ehedbor.diskordlin.entities.Snowflake


/**
 * Used to represent a user's voice connection status.
 *
 * Documentation copied from [here](https://discordapp.com/developers/docs/resources/voice#voice-state-object).
 *
 * @property guildId the guild id this voice state is for
 * @property channelId the channel id this user is connected to
 * @property userId the user id this voice state is for
 * @property member the guild member this voice state is for
 * @property sessionId the session id for this voice state
 * @property deaf whether this user is deafened by the server
 * @property mute whether this user is muted by the server
 * @property selfDeaf whether this user is locally deafened
 * @property selfMute whether this user is locally muted
 * @property suppress whether this user is muted by the current user
 */
data class VoiceState(
    @Json("guild_id") val guildId: Snowflake?,
    @Json("channel_id") val channelId: Snowflake?,
    @Json("user_id") val userId: Snowflake,
    val member: GuildMember,
    @Json("session_id") val sessionId: String,
    val deaf: Boolean,
    val mute: Boolean,
    @Json("self_deaf") val selfDeaf: Boolean,
    @Json("self_mute") val selfMute: Boolean,
    val suppress: Boolean
)