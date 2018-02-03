package testapps

import io.github.ehedbor.diskordlin.Diskordlin
import io.github.ehedbor.diskordlin.client.ClientType
import io.github.ehedbor.diskordlin.event.Events
import kotlinx.coroutines.experimental.runBlocking
import org.slf4j.LoggerFactory

private const val TOKEN = "MzkxMzYyOTQyNDIwOTEwMDgw.DTwgnw.wupFapxhzvuPsi-DIGRRiGWYaHg"
private val LOGGER = LoggerFactory.getLogger("Test")

fun main(args: Array<String>): Unit = runBlocking {
    Events.ready += {
        LOGGER.info("I called an event!")
    }
    Diskordlin.loginBlocking(TOKEN, ClientType.BOT)

    //Delay forever
    while (true) {
    }
}