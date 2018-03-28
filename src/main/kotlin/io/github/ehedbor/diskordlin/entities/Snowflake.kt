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

package io.github.ehedbor.diskordlin.entities

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import java.math.BigInteger
import java.util.*

/**
 * The unique id format used by Discord and [Twitter][https://github.com/twitter/snowflake/tree/snowflake-2010].
 */
@Suppress("MemberVisibilityCanBePrivate", "unused")
class Snowflake(private val value: BigInteger) {

    companion object : Converter<Snowflake> {
        /**
         * The first second of 2015 in Unix time.
         */
        const val DISCORD_EPOCH = 1_420_070_400_000L

        override fun fromJson(jv: JsonValue): Snowflake {
            val str = jv.string ?: throw IllegalArgumentException("Could not convert json to snowflake.")
            return Snowflake(str)
        }

        override fun toJson(value: Snowflake) = "\"$value\""
    }

    constructor(rawData: String) : this(rawData.toBigInteger())

    /** The timestamp in Unix time */
    val timestamp: Long = (value shr 22).toLong() + DISCORD_EPOCH
    val internalWorkerId: Int = (value and 0x3E0000.toBigInteger() shr 17).toInt()
    val internalProcessId: Int = (value and 0x1F000.toBigInteger() shr 12).toInt()
    val increment: Int = (value and 0xFFF.toBigInteger()).toInt()

    /** The date represented by the timestamp */
    val date = Date(timestamp)

    override fun toString() = "$value"

    override fun equals(other: Any?) = (other as? Snowflake)?.value == this.value

    override fun hashCode() = value.hashCode()
}