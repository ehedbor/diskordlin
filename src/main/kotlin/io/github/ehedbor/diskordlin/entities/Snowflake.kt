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