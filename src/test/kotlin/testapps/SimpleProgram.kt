package testapps

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.jsonObject
import io.github.ehedbor.diskordlin.BuildInfo
import io.github.ehedbor.diskordlin.Diskordlin
import io.github.ehedbor.diskordlin.client.ClientType
import io.github.ehedbor.diskordlin.entities.channel.Message
import io.github.ehedbor.diskordlin.event.Events
import io.github.ehedbor.diskordlin.util.Logger
import io.github.ehedbor.diskordlin.util.body
import kotlinx.coroutines.experimental.runBlocking
import java.util.regex.Pattern

object SimpleProgram : Logger {

    private const val TOKEN = "MzkxMzYyOTQyNDIwOTEwMDgw.DTwgnw.wupFapxhzvuPsi-DIGRRiGWYaHg"

    @JvmStatic
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
        |diskordlin {
        |    token = TOKEN
        |    type = ClientType.BOT
        |    messageCreate += ::onMessageCreated
        |    typingStart += { event ->
        |        event.reply("No typing allowed")
        |    }
        |}
        """.trimMargin()

        info(BuildInfo.version)

        //Delay forever
        while (true) {
        }
    }

    private fun onMessageCreated(message: Message) {
        if (message.content.startsWith("$")) {
            info("Received command \"${message.content}\" from ${message.author}")

            val parameters = message.content
                .substring(1)
                .split(Pattern.compile("\\n"))
            val cmd = parameters[0].toLowerCase()
            if (cmd == "greeting") {
                //message.reply("Pong!")
                "${Diskordlin.API}/channels/${message.channelId}/messages".httpPost()
                    .header(Diskordlin.HTTP_USER_AGENT)
                    .header("Authorization" to "Bot $TOKEN")
                    .body(jsonObject(
                        "content" to "Hello, ${message.author}!"
                    ))
                    .responseObject<Message> { _, _, result ->
                        when (result) {
                            is Result.Failure -> {
                                error("Error while sending message.", result.getException())
                            }
                        }
                    }

            } else {
                //message.reply("Unknown command \"$cmd\")
            }
        }
    }

}