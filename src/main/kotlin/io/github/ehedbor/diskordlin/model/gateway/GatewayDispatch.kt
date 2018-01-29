package io.github.ehedbor.diskordlin.model.gateway

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class GatewayDispatch(
    @SerializedName("op")
    val opcode: Int,
    @SerializedName("d")
    val payload: JsonElement? = null,
    @SerializedName("s")
    val sequenceNumber: Int? = null,
    @SerializedName("t")
    val eventName: String? = null
) {

    inline fun <reified T : IGatewayPayload> getMessageAs(): T? {
        return if (payload == null) null else Gson().fromJson(payload)
    }


//    @Suppress("MemberVisibilityCanPrivate")
//    val payload: IGatewayPayload? =
//        if (opcode != null && json != null) {
//            when (opcode) {
//                Opcode.DISPATCH      -> null
//                Opcode.HEARTBEAT     -> Klaxon().parse<HeartbeatPayload>(json)
//                Opcode.IDENTIFY      -> Klaxon().parse<IdentifyPayload>(json)
//                Opcode.STATUS_UPDATE -> Klaxon().parse<StatusUpdatePayload>(json)
//                Opcode.HELLO         -> Klaxon().parse<HelloPayload>(json)
//                else                 -> {
//                    println("Warning: Gateway opcode $opcode not handled!")
//                    null
//                }
//            }
//        } else {
//            null
//        }

}
