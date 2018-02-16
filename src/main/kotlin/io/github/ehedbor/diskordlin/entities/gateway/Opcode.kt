package io.github.ehedbor.diskordlin.entities.gateway

object Opcode {
    const val DISPATCH = 0
    const val HEARTBEAT = 1
    const val IDENTIFY = 2
    const val STATUS_UPDATE = 3
    const val VOICE_STATE_UPDATE = 4
    const val VOICE_SERVER_PING = 5
    const val RESUME = 6
    const val RECONNECT = 7
    const val REQUEST_GUILD_MEMBERS = 8
    const val INVALID_SESSION = 9
    const val HELLO = 10
    const val HEARTBEAT_ACK = 11

    fun nameOf(op: Int) = when (op) {
        DISPATCH              -> "DISPATCH"
        HEARTBEAT             -> "HEARTBEAT"
        IDENTIFY              -> "IDENTIFY"
        STATUS_UPDATE         -> "STATUS_UPDATE"
        VOICE_STATE_UPDATE    -> "VOICE_STATE_UPDATE"
        VOICE_SERVER_PING     -> "VOICE_SERVER_PING"
        RESUME                -> "RESUME"
        RECONNECT             -> "RECONNECT"
        REQUEST_GUILD_MEMBERS -> "REQUEST_GUILD_MEMBERS"
        INVALID_SESSION       -> "INVALID_SESSION"
        HELLO                 -> "HELLO"
        HEARTBEAT_ACK         -> "HEARTBEAT_ACK"
        else                  -> throw IllegalArgumentException("Illegal value!")
    }
}