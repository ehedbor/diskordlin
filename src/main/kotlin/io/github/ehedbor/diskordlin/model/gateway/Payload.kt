package io.github.ehedbor.diskordlin.model.gateway

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

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
    @SerializedName("op")
    val opcode: Int,
    @SerializedName("d")
    val data: JsonElement? = null,
    @SerializedName("s")
    val sequenceNumber: Int? = null,
    @SerializedName("t")
    val eventName: String? = null
) {

    companion object {
        operator fun invoke(opcode: Int, data: Any, sequenceNumber: Int? = null, eventName: String? = null): Payload {
            return Payload(opcode, Gson().toJsonTree(data), sequenceNumber, eventName)
        }
    }

    /**
     * Parses the [data] as the given type [T] using [Gson].
     */
    inline fun <reified T : Any> getDataAs(): T? {
        return if (data == null) null else Gson().fromJson(data)
    }
}
