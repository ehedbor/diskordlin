package io.github.ehedbor.diskordlin

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import io.github.ehedbor.diskordlin.client.ClientType
import io.github.ehedbor.diskordlin.client.DiscordClient
import io.github.ehedbor.diskordlin.entities.gateway.GatewayBotResponse
import io.github.ehedbor.diskordlin.util.fatal
import io.github.ehedbor.diskordlin.util.info
import kotlinx.coroutines.experimental.async

/**
 * The main Diskordlin API.
 */
@Suppress("MemberVisibilityCanBePrivate")
class Diskordlin constructor(val token: String, val clientType: ClientType) {

    private var _client: DiscordClient? = null
    internal var client: DiscordClient
        get() {
            if (_client == null) {
                throw IllegalStateException("Must log in before accessing client!")
            }
            return _client!!
        }
        private set(value) {
            _client = value
        }

    /**
     * Starts to log a [DiscordClient] into the Discord gateway API.
     *
     * @param token The authentication token.
     * @param clientType The type of client logging in.
     */
    @Suppress("RemoveExplicitTypeArguments")
    fun loginAsync() = async {
        login()
    }

    /**
     * Logs a [DiscordClient] into the Discord gateway API.
     *
     * @param token The authentication token.
     * @param clientType The type of client logging in.
     */
    fun login() {
        require(clientType == ClientType.BOT) { "Only bot clients are supported right now, sorry!" }

        val response = getBotGateway()
        // add encoding and version info
        val url = "${response.url}/?encoding=json&v=$WEBSOCKET_API_VERSION"
        // create the client endpoint
        val client = DiscordClient(token, url)

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
        internal val HTTP_USER_AGENT = "User-Agent" to "diskordlin (https://github.com/ehedbor/diskordlin, 1.0-SNAPSHOT)"
    }
}

