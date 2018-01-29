package io.github.ehedbor.diskordlin.websocket

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.github.ehedbor.diskordlin.model.gateway.GatewayDispatch
import io.github.ehedbor.diskordlin.model.gateway.HelloPayload
import io.github.ehedbor.diskordlin.model.gateway.Opcode
import io.github.ehedbor.diskordlin.util.uncompressGZip
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newSingleThreadContext
import java.net.URI
import java.util.concurrent.TimeUnit
import javax.websocket.*
import kotlin.properties.Delegates

@Suppress("MemberVisibilityCanPrivate")
@ClientEndpoint(configurator = HeaderConfigurator::class)
internal class DiscordClient(endpointUri: String) {

    private lateinit var session: Session
    private var heartbeatInterval: Int by Delegates.notNull()

    init {
        val container = ContainerProvider.getWebSocketContainer()
        container.connectToServer(this, URI(endpointUri))
    }

    @OnOpen
    internal fun onConnectionOpened(session: Session) {
        println("Starting connection!")
        this.session = session
    }

    @OnClose
    internal fun onConnectionClosed(closeReason: CloseReason) {
        println("Closing connection: $closeReason")
        session.close(closeReason)
    }

    @OnMessage
    internal fun onMessageReceived(message: String) {
        println("Received message: $message")
        val gson = Gson()
        val payload = gson.fromJson<GatewayDispatch>(message)
        when (payload.opcode) {
            Opcode.HELLO -> {
                val data = payload.getMessageAs<HelloPayload>()!!
                val interval = data.heartbeatInterval
                this.startHeartbeat(interval)
            }
            Opcode.HEARTBEAT_ACK -> {
                println("Received heartbeat acknowledgement!")
            }
        }

//        val json = Klaxon().parseJsonObject(StringReader(message))
//        val op = json["op"] as Int
//        when (op) {
//            Opcode.HELLO -> {
//                val data = json["d"] as JsonObject
//                val interval = data["heartbeat_interval"] as Int
//                this.startHeartbeat(interval.toLong())
//            }
//        }
//        IdentifyPayload(
//            "token",
//            IdentifyProperties("windows", "diskordlin", "diskordlin"),
//            true,
//            250,
//            listOf(1, 1),
//            StatusUpdatePayload(
//                33213,
//                Activity(
//                    "this is stupid",
//                    0))
//        )
//        com.beust.klaxon.json {
//
//        }
//        //packet?.payload
//        val packet = Klaxon().parser.parse(StringReader(message)) as JsonObject
//        val opcode = packet["op"] as Int
//        if (opcode == Opcode.HEARTBEAT) {
//            val payload = packet[""]
//        }
//
//
//        val dispatch = Klaxon().parse<GatewayDispatch>(message)
//        when (dispatch?.payload) {
//            is HelloPayload -> {
//                this.heartbeatInterval = dispatch.payload.heartbeatInterval!!
//            }
//        }
    }

    @OnMessage
    internal fun onBinaryMessageReceived(bytes: ByteArray)= onMessageReceived(uncompressGZip(bytes))

    @OnError
    internal fun onError(session: Session, throwable: Throwable) {
        println("Error: ${throwable.message}")
        throwable.printStackTrace()
    }

    internal fun sendMessageAsync(message: String)= session.asyncRemote.sendText(message)

    internal fun sendMessage(message: String) = session.basicRemote.sendText(message)

    private fun startHeartbeat(interval: Long) {
        val ctx = newSingleThreadContext("Heartbeat")
        launch(ctx) {
            while (true) {
                sendHeartbeat()
                delay(interval, TimeUnit.MILLISECONDS)
            }
        }
    }

    private fun sendHeartbeat() {
        val msg = GatewayDispatch(
            opcode = Opcode.HEARTBEAT,
            payload = null)

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        val jsonMessage = gson.toJson(msg)
        println("Sending heartbeat! $jsonMessage")
        this.sendMessage(jsonMessage)
    }
}