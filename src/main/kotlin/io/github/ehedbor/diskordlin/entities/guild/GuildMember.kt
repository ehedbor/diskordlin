package io.github.ehedbor.diskordlin.entities.guild

import com.beust.klaxon.Json
import io.github.ehedbor.diskordlin.entities.Snowflake
import io.github.ehedbor.diskordlin.entities.user.User


/**
 * Represents a member in a guild.
 *
 * Documentation copied from [here](https://discordapp.com/developers/docs/resources/guild#member-object).
 *
 * @property user user object
 * @property nick this users guild nickname (if one is set)
 * @property roles array of role object ids
 * @property joinedAt when the user joined the guild (ISO8601 timestamp)
 * @property deaf if the user is deafened
 * @property mute if the user is muted
 */
data class GuildMember(
    val user: User,
    val nick: String?,
    val roles: List<Snowflake> = emptyList(),
    @Json("joined_at") val joinedAt: String,
    val deaf: Boolean,
    val mute: Boolean
)