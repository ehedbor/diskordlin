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
