package io.github.ehedbor.diskordlin.model

import com.github.salomonbrys.kotson.jsonDeserializer
import com.github.salomonbrys.kotson.jsonSerializer
import com.github.salomonbrys.kotson.toJson
import java.math.BigInteger
import java.util.*

/**
 * A type of UUID used by Discord and [Twitter][https://github.com/twitter/snowflake/tree/snowflake-2010].
 */
@Suppress("MemberVisibilityCanBePrivate")
data class Snowflake(val rawData: String) {

    companion object {
        /**
         * The first second of 2015 in Unix time.
         */
        const val DISCORD_EPOCH = 1_420_070_400_000L

        val serializer get() = jsonSerializer<Snowflake> { it.src.rawData.toJson() }
        val deserializer get() = jsonDeserializer { Snowflake(it.json.asString) }
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