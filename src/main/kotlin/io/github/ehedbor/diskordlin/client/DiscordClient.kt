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

package io.github.ehedbor.diskordlin.client

import io.github.ehedbor.diskordlin.entities.channel.Channel
import io.github.ehedbor.diskordlin.entities.channel.Message
import io.github.ehedbor.diskordlin.entities.gateway.*
import io.github.ehedbor.diskordlin.entities.gateway.event.ReadyEvent
import io.github.ehedbor.diskordlin.entities.guild.UnavailableGuild
import io.github.ehedbor.diskordlin.entities.user.Activity
import io.github.ehedbor.diskordlin.entities.user.ActivityType
import io.github.ehedbor.diskordlin.entities.user.User
import io.github.ehedbor.diskordlin.event.Events
import io.github.ehedbor.diskordlin.util.Logger
import io.github.ehedbor.diskordlin.util.decompressZLib
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.serialization.json.JSON
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

    companion object : Logger

    init {
        val container = ContainerProvider.getWebSocketContainer()
        container.connectToServer(this, URI(endpointUri))
    }

    /**
     * Called when a connection is started.
     */
    @OnOpen
    fun onConnectionOpened(session: Session) {
        info("Opening websocket connection.")
        this.session = session
    }

    /**
     * Called when a connection is closed.
     */
    @OnClose
    fun onConnectionClosed(closeReason: CloseReason) {
        info("Closing websocket connection: $closeReason")
        session.close(closeReason)
    }

    /**
     * Called when a [message] is received over the websocket.
     */
    @OnMessage
    fun onMessageReceived(message: String) {
        val payload = JSON.parse<Payload>(message)

        if (payload.opcode == Opcode.DISPATCH)
            info("Received event (${payload.eventName}).")
        else
            info("Received message (${Opcode.nameOf(payload.opcode)}).")

        when (payload.opcode) {
            Opcode.DISPATCH -> handleDispatch(payload)
            Opcode.HELLO -> {
                val data = payload.getDataAs<HelloPayload>()!!
                val interval = data.heartbeatInterval
                this.startHeartbeat(interval)
            }
            Opcode.HEARTBEAT_ACK -> {
                if (!hasIdentified) {
                    this.sendIdentify()
                    hasIdentified = true
                }
            }
        }
    }

    /**
     * Called when a compressed [message] is received.
     */
    @OnMessage
    fun onBinaryMessageReceived(message: ByteArray)= onMessageReceived(decompressZLib(message))

    /**
     * Called when a [throwable] is thrown.
     */
    @OnError
    fun onError(throwable: Throwable) {
        error("An error occurred: ${throwable.message}", throwable)
    }

    fun sendMessage(message: String) = session.basicRemote.sendText(message)

    fun sendMessage(payload: Payload) =  sendMessage(JSON.stringify(payload))

    fun sendMessageAsync(message: String)= async { sendMessage(message) }

    fun sendMessageAsync(payload: Payload) = async { sendMessage(payload) }

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
            "MESSAGE_CREATE" -> {
                val msg = payload.getDataAs<Message>()!!
                Events.messageCreate(msg)
            }
            else -> {
                trace("Unhandled event type \"${payload.eventName}\"")
            }
        }
    }

    /**
     * Sends the identify payload.
     */
    private fun sendIdentify() {
        info("Sending identify payload.")

        // TODO don't hardcode the identify properties
        val identifyPayload = IdentifyPayload(
            token = token,
            properties = IdentifyProperties(
                os = System.getProperty("os.name"),
                browser = "diskordlin",
                device = "diskordlin"
            ),
            compress = false,
            largeThreshold = 250,
            //TODO change this if sharding is added
            // Shard id 0, number of shards = 1
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
        this.sendMessageAsync(JSON.stringify(payload))
    }

    /**
     * Starts sending heartbeats at the specified [interval].
     */
    private fun startHeartbeat(interval: Long) {
        info("Starting heartbeat.")
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
        info("Sending heartbeat!")

        val msg = Payload(Opcode.HEARTBEAT)
        this.sendMessage(JSON.stringify(msg))
    }
}