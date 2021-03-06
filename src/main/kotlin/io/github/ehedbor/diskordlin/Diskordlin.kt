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

package io.github.ehedbor.diskordlin

import com.github.kittinunf.fuel.httpGet
import io.github.ehedbor.diskordlin.client.ClientType
import io.github.ehedbor.diskordlin.client.DiscordClient
import io.github.ehedbor.diskordlin.entities.gateway.GatewayBotResponse
import io.github.ehedbor.diskordlin.event.EventListener
import io.github.ehedbor.diskordlin.event.EventManager
import io.github.ehedbor.diskordlin.util.Logger
import io.github.ehedbor.diskordlin.util.responseObject

/**
 * The main Diskordlin API.
 *
 * @constructor Constructs an API object.
 * @param token The user's API token. Discord uses this to connect to the API!
 * @param clientType The type of client connecting.
 */
@Suppress("MemberVisibilityCanBePrivate")
class Diskordlin constructor(val token: String, val clientType: ClientType) : Logger {

    internal lateinit var client: DiscordClient
    internal val eventManager = EventManager()

    /**
     * Adds an event to the [eventManager].
     */
    fun register(eventName: String, listener: EventListener) {
        eventManager.register(eventName, listener)
    }

    /**
     * Logs a [DiscordClient] into the Discord gateway API.
     */
    fun login() {
        require(clientType == ClientType.BOT) { "Only bot clients are supported right now, sorry!" }

        val response = getBotGateway()
        // add encoding and version info
        val url = "${response.url}/?encoding=json&v=$WEBSOCKET_API_VERSION"
        // create the client endpoint
        client = DiscordClient(this, token, url)

        info("Successfully logged in!")
    }

    private fun getBotGateway(): GatewayBotResponse {
        info("Attempting to log into Discord...")

        // Blocking get request
        val (_, _, result) = "$API/gateway/bot".httpGet()
            .header(HTTP_USER_AGENT)
            .header("Authorization" to "Bot $token")
            .responseObject<GatewayBotResponse>()
        
        result.fold(
            success = { return it },
            failure = {
                fatal("Could not log in: ${it.exception.message}")
                throw it.exception
            }
        )
    }

    companion object {
        /** The Discord API version. */
        const val API_VERSION = "6"

        /**
         * The Discord Websocket API version (Discord has separate versioning
         * schemes for the HTTP and Websocket APIs).
         */
        const val WEBSOCKET_API_VERSION = "6"

        /**
         * The URL for the Discord HTTP API.
         */
        const val API = "https://discordapp.com/api/v$API_VERSION"

        @JvmStatic
        internal val HTTP_USER_AGENT by lazy {
            "User-Agent" to "${BuildInfo.name} (${BuildInfo.url}, ${BuildInfo.version})"
        }
    }
}

/**
 * Builds a Discord API path.
 *
 * @see Diskordlin.API
 */
fun apiPath(vararg path: Any) =Diskordlin.API + path.joinToString("/")

