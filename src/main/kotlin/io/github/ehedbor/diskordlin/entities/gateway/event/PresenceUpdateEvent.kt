package io.github.ehedbor.diskordlin.entities.gateway.event

import com.beust.klaxon.Json
import io.github.ehedbor.diskordlin.entities.Snowflake
import io.github.ehedbor.diskordlin.entities.user.Activity
import io.github.ehedbor.diskordlin.entities.user.User

/**
 * A user's presence is their current state on a guild.
 * This event is sent when a user's presence is updated for a guild.
 *
 * The user object within this event can be partial, the only field which must be sent is the id
 * field, everything else is optional. Along with this limitation, no fields are required, and the
 * types of the fields are not validated. Your client should expect any combination of fields and
 * types within this event.
 *
 * Documentation copied from [here](https://discordapp.com/developers/docs/topics/gateway#presence-update).
 *
 * @property user the user presence is being updated for
 * @property roles roles this user is in
 * @property game null, or the user's current activity
 * @property guildId id of the guild
 * @property status one of any [StatusType]s.
 */
data class PresenceUpdateEvent(
    val user: User?,
    val roles: List<Snowflake> = emptyList(),
    val game: Activity?,
    @Json("guild_id") val guildId: Snowflake?,
    val status: String?
)