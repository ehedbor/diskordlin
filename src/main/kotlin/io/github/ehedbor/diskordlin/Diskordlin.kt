package io.github.ehedbor.diskordlin

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import io.github.ehedbor.diskordlin.client.ClientType
import io.github.ehedbor.diskordlin.client.DiscordClient
import io.github.ehedbor.diskordlin.exception.DiscordException
import io.github.ehedbor.diskordlin.model.gateway.GatewayBotResponse
import io.github.ehedbor.diskordlin.util.addHeaders
import kotlinx.coroutines.experimental.async

/**
 * The main Diskordlin API.
 */
object Diskordlin {

    /** The Discord API version. */
    @Suppress("MemberVisibilityCanBePrivate")
    const val API_VERSION = "6"

    /**
     * The Discord Websocket API version (Discord has separate versioning
     * schemes for the HTTP and websocket APIs).
     */
    @Suppress("MemberVisibilityCanBePrivate")
    const val WEBSOCKET_API_VERSION = "6"

    /**
     * The Discord HTTP API URL.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    const val API_URL = "https://discordapp.com/api/v$API_VERSION"

    @Suppress("MemberVisibilityCanBePrivate")
    internal const val HTTP_USER_AGENT = "diskordlin (https://github.com/ehedbor/diskordlin, 1.0-SNAPSHOT)"

    private lateinit var client: DiscordClient

    /**
     * Starts to log a [DiscordClient] into the Discord gateway API.
     *
     * @param token The authentication token.
     * @param clientType The type of client logging in.
     */
    @Suppress("RemoveExplicitTypeArguments")
    fun loginAsync(token: String, clientType: ClientType) = async<Unit> {
        loginBlocking(token, clientType)
    }

    /**
     * Logs a [DiscordClient] into the Discord gateway API.
     *
     * @param token The authentication token.
     * @param clientType The type of client logging in.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun loginBlocking(token: String, clientType: ClientType) {
        val response = getGatewayBlocking(token, clientType)
        // add encoding and version info
        val url = response.url + "/?encoding=json&v=$WEBSOCKET_API_VERSION"
        // create the client endpoint
        client = DiscordClient(token, url)
    }

    private fun getGatewayBlocking(token: String, clientType: ClientType): GatewayBotResponse {
        require(clientType == ClientType.BOT) { "Only bot clients are supported right now, sorry!" }
        // blocking get request
        val (_, _, result) = (API_URL + "/gateway/bot").httpGet()
            // These headers are required!
            .addHeaders("Authorization" to "${clientType.displayValue} $token", "User-Agent" to HTTP_USER_AGENT)
            .responseObject<GatewayBotResponse>()

        return result.component1() ?: throw DiscordException("Unable to get response from Discord API!")
    }
}

