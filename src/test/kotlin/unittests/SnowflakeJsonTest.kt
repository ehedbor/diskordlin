package unittests

import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.registerTypeAdapter
import com.google.gson.GsonBuilder
import io.github.ehedbor.diskordlin.model.Snowflake
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec


class SnowflakeJsonTest : StringSpec() {
    init {
        "Should parse correctly" {
            val gson = GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Snowflake.serializer)
                .registerTypeAdapter(Snowflake.deserializer)
                .create()

            val snowflake = Snowflake(Long.MAX_VALUE.toString())
            val str = gson.toJson(snowflake)
            print(str)

            str shouldBe "\"${Long.MAX_VALUE}\""
            gson.fromJson<Snowflake>(str) shouldBe snowflake
        }
    }
}