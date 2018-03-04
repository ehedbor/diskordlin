package io.github.ehedbor.diskordlin.entities

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.math.BigInteger
import java.util.*

/**
 * A type of UUID used by Discord and [Twitter][https://github.com/twitter/snowflake/tree/snowflake-2010].
 */
@Suppress("MemberVisibilityCanBePrivate")
@Serializable
data class Snowflake(private val rawData: String) {

    companion object {

        /**
         * The first second of 2015 in Unix time.
         */
        const val DISCORD_EPOCH = 1_420_070_400_000L
    }

    @Transient private val value = rawData.toBigInteger()

    @Transient val timestamp: Long = (value shr 22).toLong() + DISCORD_EPOCH
    @Transient val internalWorkerId: Int = ((value and BigInteger("3E0000", 16)) shr 17).toInt()
    @Transient val internalProcessId: Int = ((value and BigInteger("1F000", 16)) shr 12).toInt()
    @Transient val increment: Int = (value and BigInteger("FFF", 16)).toInt()

    @Transient  val date = Date(timestamp)

    override fun toString() = rawData

    override fun equals(other: Any?) = (other as? Snowflake)?.rawData == this.rawData

    override fun hashCode() = rawData.hashCode()


}