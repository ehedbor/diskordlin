package testapps

import io.github.ehedbor.diskordlin.Diskordlin
import io.github.ehedbor.diskordlin.client.ClientType
import io.github.ehedbor.diskordlin.event.Events
import kotlinx.coroutines.experimental.runBlocking

private const val TOKEN = "MzkxMzYyOTQyNDIwOTEwMDgw.DTwgnw.wupFapxhzvuPsi-DIGRRiGWYaHg"

fun main(args: Array<String>): Unit = runBlocking {
    Events.ready += {
        println("I called an event!")
    }
    Diskordlin.loginBlocking(TOKEN, ClientType.BOT)

    //Delay forever
    while (true) {
    }
}