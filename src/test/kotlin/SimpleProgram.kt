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

import io.github.ehedbor.diskordlin.BuildInfo
import io.github.ehedbor.diskordlin.Diskordlin
import io.github.ehedbor.diskordlin.client.ClientType
import io.github.ehedbor.diskordlin.entities.channel.Message
import io.github.ehedbor.diskordlin.event.Events
import io.github.ehedbor.diskordlin.util.Logger
import kotlinx.coroutines.experimental.runBlocking

object SimpleProgram : Logger {

    private lateinit var token: String

    @JvmStatic
    fun main(args: Array<String>): Unit = runBlocking {
        token = args[0]
        Diskordlin(token, ClientType.BOT).apply {
            Events.messageCreate += SimpleProgram::onMessageCreated
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
//        if (message.content.startsWith("$")) {
//            info("Received command \"${message.content}\" from ${message.author}")
//
//            val parameters = message.content
//                .substring(1)
//                .split(Pattern.compile("\\n"))
//            val cmd = parameters[0].toLowerCase()
//            if (cmd == "greeting") {
//                //message.reply("Pong!")
//                "${Diskordlin.API}/channels/${message.channelId}/messages".httpPost()
//                    .header(Diskordlin.HTTP_USER_AGENT)
//                    .header("Authorization" to "Bot $token")
//                    .body(jsonObject(
//                        "content" to "Hello, ${message.author}!"
//                    ))
//                    .responseObject<Message> { _, _, result ->
//                        when (result) {
//                            is Result.Failure -> {
//                                error("Error while sending message.", result.getException())
//                            }
//                        }
//                    }
//
//            } else {
//                //message.reply("Unknown command \"$cmd\")
//            }
//        }
    }

}