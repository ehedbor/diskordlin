package io.github.ehedbor.diskordlin.unittests

import io.github.ehedbor.diskordlin.model.chat.*
import io.github.ehedbor.diskordlin.model.user.User
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class ConstructorTest : StringSpec() {
    init {
        val embed = Embed(title = "Title",
            footer = EmbedFooter("Footer Text"),
            image = EmbedImage(
                "https://kotlin.is/neat/",
                height = 1340,
                width = 943
            )
        )

        val message = Message(id = "hello",
            timestamp = "3281998322",
            embeds = listOf(embed),
            type = MessageType.DEFAULT,
            author = User(
                "hi",
                "yo",
                "3213"
            ),
            channelId = "2", content = "What is up dud!",
            mentionEveryone = false,
            pinned = false,
            tts = false)

        message.id shouldBe "hello"
        message.timestamp shouldBe "3281998322"
        message.type shouldBe MessageType.DEFAULT

        val msgEmbed = message.embeds[0]
        msgEmbed.title shouldBe "Title"
        msgEmbed.footer?.text shouldBe "Footer Text"
        msgEmbed.image?.url shouldBe "https://kotlin.is/neat/"
        msgEmbed.image?.height shouldBe 1340
        msgEmbed.image?.width shouldBe 943
    }
}