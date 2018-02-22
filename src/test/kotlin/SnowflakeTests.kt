import io.github.ehedbor.diskordlin.entities.Snowflake
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import kotlinx.serialization.json.JSON

class SnowflakeTests : StringSpec() {
    init {
        "Should correctly parse" {
            val json = """
                "1234567890313"
            """.trimIndent()
            val obj = JSON.parse<Snowflake>(json)
            obj shouldBe Snowflake("1234567890313")
        }
    }
}