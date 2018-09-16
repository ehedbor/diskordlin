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

import com.github.kittinunf.fuel.httpPost
import io.github.ehedbor.diskordlin.Diskordlin
import io.github.ehedbor.diskordlin.apiPath
import io.github.ehedbor.diskordlin.client.ClientType
import io.github.ehedbor.diskordlin.entities.channel.Message
import io.github.ehedbor.diskordlin.event.EventArgs
import io.github.ehedbor.diskordlin.util.Logger
import io.github.ehedbor.diskordlin.util.responseObject
import kotlinx.coroutines.experimental.runBlocking

object SimpleProgram : Logger {

    private lateinit var token: String

    @JvmStatic
    fun main(args: Array<String>): Unit = runBlocking {
        token = args[0]
        Diskordlin(token, ClientType.BOT).apply {
            register("READY") {
                info("Hello, events!")
            }
            register("MESSAGE_CREATE", ::onMessageCreated)
            login()
        }

        //Delay forever
        while (true) {}
    }

    private fun onMessageCreated(args: EventArgs) {
        info("Now handling MESSAGE_CREATE")
        val msg = args["message"] as Message
        val reply = """{"content"="Hello, ${msg.author}!"}"""

        if (!msg.content.startsWith("\$hi")) return

        apiPath("channels", msg.channelId, "messages")
            .httpPost(listOf("content" to "Hello, ${msg.author}"))
            .header(Diskordlin.HTTP_USER_AGENT)
            .header("Authorization" to "Bot $token")
            .responseObject<Message> { request, response, result ->
                result.fold(success = {
                    info("I sent a message!")
                }, failure = {
                    error("I did not send a message!", it)
                })
            }
    }
}