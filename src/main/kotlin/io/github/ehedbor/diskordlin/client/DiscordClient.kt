package io.github.ehedbor.diskordlin.client

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.github.ehedbor.diskordlin.Diskordlin.LOGGER
import io.github.ehedbor.diskordlin.event.Events
import io.github.ehedbor.diskordlin.model.chat.Channel
import io.github.ehedbor.diskordlin.model.chat.UnavailableGuild
import io.github.ehedbor.diskordlin.model.gateway.*
import io.github.ehedbor.diskordlin.model.gateway.event.ReadyEvent
import io.github.ehedbor.diskordlin.model.user.Activity
import io.github.ehedbor.diskordlin.model.user.ActivityType
import io.github.ehedbor.diskordlin.model.user.User
import io.github.ehedbor.diskordlin.util.uncompressGZip
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newSingleThreadContext
import java.net.URI
import java.util.concurrent.TimeUnit
import javax.websocket.*

/**
 * Handles the websocket client.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
@ClientEndpoint
internal class DiscordClient(val token: String, endpointUri: String) {

    private lateinit var session: Session
    private var hasIdentified: Boolean = false

    private var userInfo: User? = null
    private var dmChannels: List<Channel>? = null
    private var joinedGuilds: List<UnavailableGuild>? = null
    private var sessionId: String? = null

    init {
        val container = ContainerProvider.getWebSocketContainer()
        container.connectToServer(this, URI(endpointUri))
    }

    /**
     * Called when a connection is started.
     */
    @OnOpen
    internal fun onConnectionOpened(session: Session) {
        LOGGER.info("Opening websocket connection.")
        this.session = session
    }

    /**
     * Called when a connection is closed.
     */
    @OnClose
    internal fun onConnectionClosed(closeReason: CloseReason) {
        LOGGER.info("Closing websocket connection: $closeReason")
        session.close(closeReason)
    }

    /**
     * Called when a [message] is received over the websocket.
     */
    @OnMessage
    internal fun onMessageReceived(message: String) {
        val gson = Gson()
        val payload = gson.fromJson<Payload>(message)

        LOGGER.info("Received message with opcode ${payload.opcode}.")

        when (payload.opcode) {
            Opcode.HELLO -> {
                val data = payload.getDataAs<HelloPayload>()!!
                val interval = data.heartbeatInterval
                this.startHeartbeat(interval)
            }
            Opcode.HEARTBEAT_ACK -> {
                if (!hasIdentified) {
                    this.identify()
                    hasIdentified = true
                }
            }
            Opcode.DISPATCH -> handleDispatch(payload)
        }
    }

    /**
     * Called when a compressed [message] is received.
     */
    @OnMessage
    internal fun onBinaryMessageReceived(message: ByteArray)= onMessageReceived(uncompressGZip(message))

    /**
     * Called when a [throwable] is thrown.
     */
    @OnError
    internal fun onError(throwable: Throwable) {
        LOGGER.error("An error occurred: ${throwable.message}", throwable)
    }

    /**
     * Sends a [message] asynchronously.
     */
    internal fun sendMessageAsync(message: String)= session.asyncRemote.sendText(message)

    /**
     * Sends a [message] (blocking).
     */
    internal fun sendMessage(message: String) = session.basicRemote.sendText(message)

    /**
     * Handles [Opcode.DISPATCH].
     */
    private fun handleDispatch(payload: Payload) {
        when (payload.eventName) {
            "READY" -> {
                val data = payload.getDataAs<ReadyEvent>()!!
                this.userInfo = data.user
                this.dmChannels = data.privateChannels
                this.joinedGuilds = data.guilds
                this.sessionId = data.sessionId
                Events.ready(data)
            }
        }
    }

    /**
     * Sends an identify payload.
     */
    private fun identify() {
        LOGGER.info("Sending identify payload.")
        // TODO don't hardcode the identify properties
        val identifyPayload = IdentifyPayload(
            token = token,
            properties = IdentifyProperties(
                os = System.getProperty("os.name"),
                browser = "diskordlin",
                device = "diskordlin"
            ),
            compress = false,
            largeThreshold = 100,
            shard = listOf(0, 1),
            presence = StatusUpdate(
                since = null,
                game = Activity(
                    "IntelliJ IDEA",
                    ActivityType.GAME
                ),
                status = StatusType.ONLINE,
                afk = false
            )
        )
        val payload = Payload(Opcode.IDENTIFY, identifyPayload)
        this.sendMessageAsync(Gson().toJson(payload))
    }

    /**
     * Starts sending heartbeats at the specified [interval].
     */
    private fun startHeartbeat(interval: Long) {
        LOGGER.info("Starting heartbeat.")
        val ctx = newSingleThreadContext("Heartbeat")
        launch(ctx) {
            while (true) {
                sendHeartbeat()
                delay(interval, TimeUnit.MILLISECONDS)
            }
        }
    }

    /**
     * Sends a heartbeat.
     */
    private fun sendHeartbeat() {
        LOGGER.info("Sending heartbeat!")

        val msg = Payload(Opcode.HEARTBEAT)
        val gson = GsonBuilder()
            .serializeNulls()
            .create()
        this.sendMessage(gson.toJson(msg))
    }
}