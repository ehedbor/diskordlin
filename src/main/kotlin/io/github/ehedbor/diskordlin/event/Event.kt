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

package io.github.ehedbor.diskordlin.event

/**
 * Represents an event that can be subscribed to and invoked.
 *
 * @param T The parameter that this event takes.
 */
class Event<T> {

    private val listeners: MutableMap<String?, MutableList<EventListener<T>>> = mutableMapOf()

    @JvmName("put")
    operator fun plusAssign(handler: EventListener<T>) = set(null, handler)

    @JvmName("put")
    operator fun plusAssign(namedHandler: Pair<String?, EventListener<T>>) = set(namedHandler.first, namedHandler.second)

    @JvmName("put")
    operator fun set(eventName: String?, eventHandler: EventListener<T>) {
        if (listeners[eventName] == null) {
            listeners[eventName] = mutableListOf()
        }
        listeners[eventName]!! += eventHandler
    }

    /**
     * Removes all events associated with this identifier.
     */
    @JvmName("remove")
    operator fun minusAssign(eventName: String?) {
        listeners.remove(eventName)
    }

    /**
     * Executes every listener to this event.
     */
    operator fun invoke(parameter: T) {
        for ((_, listenerList) in listeners) {
            listenerList.forEach { it(parameter) }
        }
    }
}