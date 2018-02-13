package io.github.ehedbor.diskordlin

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.responseObject
import io.github.ehedbor.diskordlin.client.ClientType
import io.github.ehedbor.diskordlin.client.DiscordClient
import io.github.ehedbor.diskordlin.model.gateway.GatewayBotResponse
import kotlinx.coroutines.experimental.async
import org.slf4j.LoggerFactory

/**
 * The main Diskordlin API.
 */
@Suppress("MemberVisibilityCanBePrivate")
object Diskordlin {

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

    internal const val HTTP_USER_AGENT = "diskordlin (https://github.com/ehedbor/diskordlin, 1.0-SNAPSHOT)"

    @JvmStatic
    internal val LOGGER = LoggerFactory.getLogger(Diskordlin::class.java)

    private lateinit var client: DiscordClient

    /**
     * Starts to log a [DiscordClient] into the Discord gateway API.
     *
     * @param token The authentication token.
     * @param clientType The type of client logging in.
     */
    @Suppress("RemoveExplicitTypeArguments")
    fun loginAsync(token: String, clientType: ClientType) = async<Unit> {
        login(token, clientType)
    }

    /**
     * Logs a [DiscordClient] into the Discord gateway API.
     *
     * @param token The authentication token.
     * @param clientType The type of client logging in.
     */
    fun login(token: String, clientType: ClientType) {
        require(clientType == ClientType.BOT) { "Only bot clients are supported right now, sorry!" }

        val response = getBotGateway(token)
        // add encoding and version info
        val url = response.url + "/?encoding=json&v=$WEBSOCKET_API_VERSION"
        // create the client endpoint
        client = DiscordClient(token, url)

        LOGGER.info("Successfully logged in!")
    }

    private fun getBotGateway(token: String): GatewayBotResponse {
        LOGGER.info("Attempting to log into Discord...")

        // Blocking get request
        val (_, _, result) = Fuel.get(API + "/gateway/bot")
            .header("User-Agent" to HTTP_USER_AGENT)
            .header("Authorization" to "Bot $token")
            .responseObject<GatewayBotResponse>()

        result.fold(
            success = { return it },
            failure = { throw it.exception }
        )
    }
}

