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
import io.github.ehedbor.diskordlin.util.withDefaultConverters
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
data class Payload(
    @Json("op") val opcode: Int,
    @Json("d") val data: JsonObject? = null,
    @Json("s") val sequenceNumber: Int? = null,
    @Json("t") val eventName: String? = null
) {

    // stores the internal representation of the data
    private var parsedData: Any? = null

    init {
        // some state checking
        if (opcode != Opcode.DISPATCH) {
            check(sequenceNumber == null) { "Sequence numbers are only for Opcode.DISPATCH" }
            check(eventName == null) { "Event names are only for Opcode.DISPATCH" }
        }
    }

    /**
     * Constructs a [Payload] object with an [innerPayload].
     */
    constructor(opcode: Int, innerPayload: Any?, sequenceNumber: Int? = null, eventName: String? = null) :
        this(
            opcode = opcode,
            data = null,
            sequenceNumber = sequenceNumber,
            eventName = eventName
        ) {
        parsedData = innerPayload
    }

    companion object : Converter<Payload> {
        override fun fromJson(jv: JsonValue): Payload {
            val opcode = jv.obj?.int("op")
            val data = jv.obj?.obj("d")
            val sequenceNumber = jv.obj?.int("s")
            val eventName = jv.obj?.string("t")

            requireNotNull(opcode) { "Opcode must be present!" }
            return Payload(opcode!!, data, sequenceNumber, eventName)
        }

        override fun toJson(value: Payload): String? {
            // capture a copy of parsedData so that it can be smart cast to Any
            val tempParsedData = value.parsedData

            val dataJson = when {
                value.data != null -> value.data.toJsonString()
                tempParsedData != null -> Klaxon().withDefaultConverters().toJsonString(tempParsedData)
                else -> null
            }

            return """{"op":${value.opcode},"d":$dataJson,"s":${value.sequenceNumber},"t":"${value.eventName}"}"""
        }
    }

    /**
     * Parses the [data] as the given type [T].
     */
    inline fun <reified T : Any> getParsedDataAs() = getParsedDataAs(T::class)

    /**
     * Parses the [data] as the given [kclass].
     */
    fun <T : Any> getParsedDataAs(kclass: KClass<T>): T? {
        return if (parsedData == null) {
            data?.let {
                // Cache the parsed value once it is created
                val parsed = kclass.safeCast(Klaxon().withDefaultConverters().fromJsonObject(data, kclass.java, kclass))
                parsedData = parsed
                parsed
            }
        } else {
            kclass.safeCast(parsedData)
        }
    }
}
