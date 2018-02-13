package unittests

import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.registerTypeAdapter
import com.google.gson.GsonBuilder
import io.github.ehedbor.diskordlin.model.Snowflake
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec


class InlineTest : StringSpec() {

    private data class SomeData(val id: Snowflake)

    private inline fun <reified T : Any> parse(str: String): T {
        val gson = GsonBuilder()
            .registerTypeAdapter(Snowflake.deserializer)
            .create()
        return gson.fromJson(str)
    }

    init {
        "Should not crash" {
            val json = """
                {
                    "s": "12345678932134213"
                }
            """.trimIndent()
            val data = parse<SomeData>(json)
            data.id.rawData shouldBe "12345678932134213"
        }
    }
}