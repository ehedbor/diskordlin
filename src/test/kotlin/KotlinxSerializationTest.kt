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

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import io.github.ehedbor.diskordlin.entities.Snowflake
import io.github.ehedbor.diskordlin.entities.TestEntity
import io.github.ehedbor.diskordlin.entities.gateway.IdentifyPayload
import io.github.ehedbor.diskordlin.entities.gateway.Opcode
import io.github.ehedbor.diskordlin.entities.gateway.Payload
import io.github.ehedbor.diskordlin.entities.user.User
import io.kotlintest.matchers.shouldEqual
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.StringSpec
import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl
import kotlinx.serialization.json.JSON
import java.io.StringReader
import java.math.BigInteger
import java.util.*

@Serializable
data class SimpleDataClass(var myProperty: String)

// Literally just a copy of Snowflake
@Serializable
data class SnowflakeCopy(private val value: BigInteger) {

    @Serializer(forClass = SnowflakeCopy::class)
    companion object : KSerializer<SnowflakeCopy> {

        /**
         * The first second of 2015 in Unix time.
         */
        const val DISCORD_EPOCH = 1_420_070_400_000L

        override fun load(input: KInput): SnowflakeCopy {
            val rawData = input.readStringValue()
            return SnowflakeCopy(rawData)
        }

        override fun save(output: KOutput, obj: SnowflakeCopy) {
            output.writeStringValue(obj.value.toString())
        }

        override val serialClassDesc: KSerialClassDesc
            = SerialClassDescImpl("io.github.ehedbor.diskordlin.entities.SnowflakeCopy")
    }

    constructor(rawData: String) : this(rawData.toBigInteger())
    constructor(value: Int)      : this(value.toBigInteger())
    constructor(value: Long)     : this(value.toBigInteger())

    /** The timestamp in Unix time */
    @Transient val timestamp: Long = (value shr 22).toLong() + DISCORD_EPOCH
    @Transient val internalWorkerId: Int = (value and 0x3E0000.toBigInteger() shr 17).toInt()
    @Transient val internalProcessId: Int = (value and 0x1F000.toBigInteger() shr 12).toInt()
    @Transient val increment: Int = (value and 0xFFF.toBigInteger()).toInt()

    /** The date represented by the timestamp */
    @Transient val date = Date(timestamp)

    override fun toString() = value.toString()

    override fun equals(other: Any?) = (other as? SnowflakeCopy)?.value == this.value

    override fun hashCode() = value.hashCode()
}

//@Serializer(forClass = BigInteger::class)
//object BigIntegerSerializer : KSerializer<BigInteger>

class KotlinxSerializationTest : StringSpec() {
    init {
        "Serialize and deserialize a simple object correctly" {
            val originalObject = SimpleDataClass("some value")
            val jsonRepresentation = JSON.stringify(originalObject)
            val parsedObject = JSON.parse<SimpleDataClass>(jsonRepresentation)

            println(jsonRepresentation)
            originalObject shouldEqual parsedObject
        }

        "Serialize a basic class from src/main" {
            val original = TestEntity(listOf("please work now"))
            val json = JSON.stringify(original)
            val parsed = JSON.parse<TestEntity>(json)

            println(json)
            original shouldEqual parsed
        }
                                  
        "Serialize another snowflake-like class" {
            val original = SnowflakeCopy("1234567890")
            val json = JSON.stringify(original)
            val parsed = JSON.parse<SnowflakeCopy>(json)

            println(json)
            original shouldEqual parsed
        }

        "Serialize a snowflake correctly" {
            val original = Snowflake("1234567890")
            val json = JSON.stringify(original)
            val parsed = JSON.parse<Snowflake>(json)

            println(json)
            original shouldEqual parsed
        }

        "Serialize a payload correctly" {
            val original = Payload(
                opcode = Opcode.HEARTBEAT
                //data = 3.toString()
            )
            val json = JSON.stringify(original)
            val parsed = JSON.parse<Payload>(json)

            println(json)
            original shouldEqual parsed
        }

        "Serialize and deserialize a User" {
            val original = User(
                Snowflake("38931893123"),
                "Awesome dude",
                "3214",
                "some awesome avatar",
                false,
                true,
                true,
                "awesome@google.notascam.com"
            )
            val json = JSON.stringify(original)
            val parsed = JSON.parse<User>(json)

            println(json)
            original shouldEqual parsed
        }

        "Try klaxon for parsing" {
            val json = """
                {
                    "op": 2,
                    "d": {
                        "token": "sup"
                        "properties": {
                            "os": "no",
                            "browser": "4",
                            "device": "hi"
                        },
                        "compress": false,
                        "largeThreshold": 4,
                        "presence": {
                            "status": "offline",
                            "afk": true
                        }
                        "shard": null
                    }
                }
            """.trimIndent()
            val obj = Klaxon().parseJsonObject(StringReader(json))
            when (obj["op"] as Int) {
                Opcode.IDENTIFY -> {
                    val payload = Klaxon().parseFromJsonObject<IdentifyPayload>(obj["d"] as JsonObject)
                    payload?.shard shouldNotBe null
                }
            }

            val payload = Klaxon().converter(Payload).parse<Payload>(json)
            when (payload?.opcode) {
                Opcode.IDENTIFY -> {
                    payload.getDataAs<IdentifyPayload>()!!
                }
            }
        }

        "More klaxon" {
            val json = """
                {
                    "something": 4,
                    "jsonObj": {
                        "anotherField": "exists"
                    }
                }
                """
            val obj = Klaxon()
                .converter(JsonHolder)
                .parse<JsonHolder>(json)
            println(obj)
        }
    }
}

data class JsonHolder(val something: Int, var jsonObj: Any?) {
    companion object : Converter<JsonHolder> {
        override fun fromJson(jv: JsonValue): JsonHolder {
            requireNotNull(jv.obj) { "Must be a json object!" }
            jv.obj?.let {
                val something = it.int("something")
                requireNotNull(something) { "Field something must be present" }

                val jsonObj = it.obj("jsonObj")
                requireNotNull(jsonObj) { "Field jsonObj must be present" }

                var actual: Any? = null
                if (something == 4) {
                    val anotherField = jsonObj?.string("anotherField")
                    requireNotNull(anotherField) { "blah blah blah" }
                    actual = InnerJsonObject(anotherField!!)
                }

                return JsonHolder(something!!, actual)
            }
            throw IllegalStateException("Unable to create object.")
        }

        override fun toJson(value: JsonHolder): String? {
            if (value.something == 4) {
                val objAsInner = value.jsonObj as? InnerJsonObject
                checkNotNull(objAsInner) { "wat" }

                return """{"something":4,"jsonObj":{"anotherField":"${objAsInner!!.anotherField}"}}"""
            }
            return """{"something":${value.something},"jsonObj":null}"""
        }
    }
}
data class InnerJsonObject(val anotherField: String)