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