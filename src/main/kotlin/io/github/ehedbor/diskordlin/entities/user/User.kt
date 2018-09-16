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

package io.github.ehedbor.diskordlin.entities.user

import com.beust.klaxon.Json
import io.github.ehedbor.diskordlin.entities.Snowflake

/**
 * Represents a Discord user.
 *
 * Users in Discord are generally considered the base entity. Users can spawn across the entire
 * platform, be members of guilds, participate in text and voice chat, and much more. Users are
 * separated by a distinction of "bot" vs "normal." Although they are similar, bot users are
 * automated users that are "owned" by another user. Unlike normal users, bot users do not have a
 * limitation on the number of Guilds they can be a part of.
 *
 * Documentation copied from [here](https://discordapp.com/developers/docs/resources/user).
 *
 * @property id the user's id
 * @property username the user's username, not unique across the platform
 * @property discriminator the user's 4-digit discord-tag
 * @property avatar the user's avatar hash
 * @property isBot whether the user belongs to an OAuth2 application
 * @property mfaEnabled whether the user has two factor enabled on their account
 * @property locale the user's chosen language option
 * @property verified whether the email on this account has been verified
 * @property email the user's email
 */
@Suppress("MemberVisibilityCanBePrivate")
data class User(
    val id: Snowflake,
    val username: String,
    val discriminator: String,
    val avatar: String? = null,
    @Json("bot") val isBot: Boolean = false,
    @Json("mfa_enabled") val mfaEnabled: Boolean = false,
    val locale: String? = null,
    val verified: Boolean = false,
    val email: String? = null
) {

    override fun toString() = username
}