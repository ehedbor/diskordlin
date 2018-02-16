package testapps

import io.github.ehedbor.diskordlin.Diskordlin
import io.github.ehedbor.diskordlin.client.ClientType
import io.github.ehedbor.diskordlin.entities.channel.Message
import io.github.ehedbor.diskordlin.event.Events
import io.github.ehedbor.diskordlin.util.info
import kotlinx.coroutines.experimental.runBlocking
import java.util.regex.Pattern

object SimpleProgram {

    private const val TOKEN = "MzkxMzYyOTQyNDIwOTEwMDgw.DTwgnw.wupFapxhzvuPsi-DIGRRiGWYaHg"

    fun main(args: Array<String>): Unit = runBlocking {

        Diskordlin(TOKEN, ClientType.BOT).apply {
            Events.messageCreate += ::onMessageCreated

            Events.ready += {
                info("Hello")
            }
            login()
        }
        // What I'd like the api to look like in the future
        """
        diskordlin {
            token = TOKEN
            type = ClientType.BOT
            messageCreate += ::onMessageCreated
            typingStart += { event ->
                event.reply("No typing allowed")
            }
        }
       """.trimMargin()

        //Delay forever
        while (true) {
        }
    }

    private fun onMessageCreated(message: Message) {
        if (message.content.startsWith("$")) {
            info("Received command ${message.content}")

            val parameters = message.content
                .substring(1)
                .split(Pattern.compile("\\n"))
            val cmd = parameters[0].toLowerCase()
            if (cmd == "greeting") {
                //message.reply("Pong!")
            } else {
                //message.reply("Unknown command \"$cmd\")
            }
        }
    }
}