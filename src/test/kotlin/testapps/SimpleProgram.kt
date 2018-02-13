package testapps

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.responseObject
import io.github.ehedbor.diskordlin.Diskordlin
import io.github.ehedbor.diskordlin.client.ClientType
import io.github.ehedbor.diskordlin.event.Events
import io.github.ehedbor.diskordlin.model.chat.Message
import kotlinx.coroutines.experimental.runBlocking
import org.slf4j.LoggerFactory

private const val TOKEN = "MzkxMzYyOTQyNDIwOTEwMDgw.DTwgnw.wupFapxhzvuPsi-DIGRRiGWYaHg"
private val LOGGER = LoggerFactory.getLogger("Test")

fun main(args: Array<String>): Unit = runBlocking {
    Events.ready += {
        LOGGER.info("I called an event!")
    }
    Events.ready["Hello"] = {
        LOGGER.info("Called event hello!")
    }
    //Events.messageCreate += ::onMessageCreated

    Diskordlin.login(TOKEN, ClientType.BOT)

    //Delay forever
    while (true) {
    }
}

fun onMessageCreated(message: Message) {
    if (message.content.contains("gay")) {
        val channelId = message.channelId
        Fuel.post(Diskordlin.API + "/channels/$channelId/messages")
            .body("{\"content\":\"thats gay\"}")
            .responseObject<Message> { _, _, result ->
                val (_, error) = result
                if (error != null) {
                    LOGGER.warn("Error occured while trying to send message!")
                }
            }
    }
}