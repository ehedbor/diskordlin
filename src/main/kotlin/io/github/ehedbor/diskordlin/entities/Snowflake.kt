package io.github.ehedbor.diskordlin.entities

import com.github.salomonbrys.kotson.jsonDeserializer
import com.github.salomonbrys.kotson.jsonSerializer
import com.github.salomonbrys.kotson.toJson
import kotlinx.serialization.*
import java.math.BigInteger
import java.util.*

/**
 * A type of UUID used by Discord and [Twitter][https://github.com/twitter/snowflake/tree/snowflake-2010].
 */
@Suppress("MemberVisibilityCanBePrivate")
@Serializable
data class Snowflake(private val rawData: String) {

    @Serializer(forClass = Snowflake::class)
    companion object : KSerializer<Snowflake> {

        /**
         * The first second of 2015 in Unix time.
         */
        const val DISCORD_EPOCH = 1_420_070_400_000L

        val serializer get() = jsonSerializer<Snowflake> { it.src.rawData.toJson() }
        val deserializer get() = jsonDeserializer { Snowflake(it.json.asString) }

        override fun load(input: KInput) = Snowflake(input.readStringValue())

        override fun save(output: KOutput, obj: Snowflake) = output.writeStringValue("$obj")
    }

    private val value = rawData.toBigInteger()

    val timestamp = (value shr 22).toLong() + DISCORD_EPOCH
    val internalWorkerId = ((value and BigInteger("3E0000", 16)) shr 17).toInt()
    val internalProcessId = ((value and BigInteger("1F000", 16)) shr 12).toInt()
    val increment = (value and BigInteger("FFF", 16)).toInt()

    val date = Date(timestamp)

    override fun toString() = rawData

    override fun equals(other: Any?) = (other as? Snowflake)?.value == this.value

    override fun hashCode() = value.hashCode()


}