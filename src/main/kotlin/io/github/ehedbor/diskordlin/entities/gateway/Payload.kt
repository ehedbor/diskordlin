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

package io.github.ehedbor.diskordlin.entities.gateway

import com.beust.klaxon.*
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass
import kotlin.reflect.full.safeCast

/**
 * The payload that is sent over the Discord gateway.
 *
 * @constructor Constructs a [Payload] object.
 * @property opcode The [Payload]'s opcode. A full list of opcodes can be found in [Opcode].
 * @property data Event data.
 * @property sequenceNumber Used for resuming sessions and heartbeats (only for [Opcode.DISPATCH]).
 * @property eventName The event name of this [Payload] (only for [Opcode.DISPATCH]).
 */
@Suppress("MemberVisibilityCanBePrivate")
@Serializable
data class Payload(
    @Json(name="op") val opcode: Int,
    @Json(name="d") val data: JsonObject? = null,
    @Json(name="s") val sequenceNumber: Int? = null,
    @Json(name="t") val eventName: String? = null
) {

    var parsedData: Any? = null
        private set

    constructor(opcode: Int, innerPayload: Any?, sequenceNumber: Int? = null, eventName: String? = null)
        : this(
        opcode = opcode,
        data = null,
        sequenceNumber = sequenceNumber,
        eventName = eventName) {
        parsedData = innerPayload
    }

    companion object : Converter<Payload> {
        override fun fromJson(jv: JsonValue): Payload {
            val opcode = jv.obj?.int("op")
            val data = jv.obj?.obj("d")
            val sequenceNumber = jv.obj?.int("s")
            val eventName = jv.obj?.string("eventName")

            requireNotNull(opcode) { "Opcode must be present!" }
            return Payload(opcode!!, data, sequenceNumber, eventName)
        }

        override fun toJson(value: Payload): String? {
            TODO("Not implemented")
        }
    }

    /**
     * Parses the [data] as the given type [T].
     */
    inline fun <reified T : Any> getDataAs() = getDataAs(T::class)

    /**
     * Parses the [data] as the given [type].
     */
    fun <T : Any> getDataAs(type: KClass<T>): T? {
        return if (parsedData == null) {
            if (data == null) {
                null
            } else {
                // Cache the parsed value once it is created
                val obj = type.safeCast(Klaxon().fromJsonObject(data, type.java, type))
                parsedData = obj
                obj
            }
        } else {
            type.safeCast(parsedData)
        }
    }
}
