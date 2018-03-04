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

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JSON
import kotlinx.serialization.SerialName as Name

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
data class Payload @JvmOverloads constructor(
    @Name("op") val opcode: Int,
    @Optional @Name("d") val data: String? = null,
    @Optional @Name("s") val sequenceNumber: Int? = null,
    @Optional @Name("t") val eventName: String? = null
) {

    /**
     * Constructs a [Payload] object by stringifying the [data] and calling the primary constructor.
     */
    constructor(opcode: Int, data: Any, sequenceNumber: Int? = null, eventName: String? = null)
        : this(opcode, JSON.stringify(data) as String?, sequenceNumber, eventName)

    /**
     * Parses the [data] as the given type [T] using [Gson].
     */
    inline fun <reified T : Any> getDataAs() = if (data == null) null else JSON.parse<T>(data)
}
