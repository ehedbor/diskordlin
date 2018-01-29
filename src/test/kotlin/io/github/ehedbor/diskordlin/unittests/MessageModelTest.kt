package io.github.ehedbor.diskordlin.unittests

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import io.github.ehedbor.diskordlin.model.chat.Emoji
import io.github.ehedbor.diskordlin.model.chat.Message
import io.github.ehedbor.diskordlin.model.user.User
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.StringSpec


class MessageModelTest : StringSpec() {
    init {
        "Should parse correctly" {
            val message = Gson().fromJson<Message>("""
                |{
                |    "reactions": [
                |        {
                |            "count": 1,
                |            "me": false,
                |            "emoji": {
                |                "id": null,
                |                "name": "🔥"
                |            }
                |        }
                |    ],
                |    "attachments": [],
                |    "tts": false,
                |    "embeds": [],
                |    "timestamp": "2017-07-11T17:27:07.299000+00:00",
                |    "mention_everyone": false,
                |    "id": "334385199974967042",
                |    "pinned": false,
                |    "edited_timestamp": null,
                |    "author": {
                |        "username": "Mason",
                |        "discriminator": "9999",
                |        "id": "53908099506183680",
                |        "avatar": "a_bab14f271d565501444b2ca3be944b25"
                |    },
                |    "mention_roles": [],
                |    "content": "Supa Hot",
                |    "channel_id": "290926798999357250",
                |    "mentions": [],
                |    "type": 0
                |}
            """.trimMargin())

            message shouldNotBe null

            val reaction = message!!.reactions[0]
            reaction.count shouldBe 1
            reaction.me shouldBe false
            reaction.emoji shouldBe Emoji(id = null, name = "🔥")

            message.attachments.isEmpty() shouldBe true
            message.tts shouldBe false
            message.embeds.isEmpty() shouldBe true
            message.timestamp shouldBe "2017-07-11T17:27:07.299000+00:00"
            message.mentionEveryone shouldBe false
            message.id shouldBe "334385199974967042"
            message.pinned shouldBe false
            message.editedTimestamp shouldBe null
            message.author shouldBe User(username = "Mason", discriminator = "9999",
                id = "53908099506183680", avatar = "a_bab14f271d565501444b2ca3be944b25")
            message.mentionRoles.isEmpty() shouldBe true
            message.content shouldBe "Supa Hot"
            message.channelId shouldBe "290926798999357250"
            message.mentions.isEmpty() shouldBe true
            message.type shouldBe 0
        }
    }
}