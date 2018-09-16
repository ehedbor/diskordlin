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
 * Represents a method that is called by [Event]s.
 */
typealias EventListener = (EventArgs) -> Unit

/**
 * Represents an event that can be listened to and invoked.
 */
class Event {

    private val listeners: MutableList<EventListener> = mutableListOf()

    @JvmName("add")
    operator fun plusAssign(eventHandler: EventListener) {
        listeners += eventHandler
    }

    /**
     * Removes all listeners associated with this event.
     */
    fun removeAll(eventName: String?) {
        listeners.removeAll { true }
    }

    /**
     * Executes every listener to this event.
     */
    operator fun invoke(args: EventArgs) {
        for (listener in listeners) {
            listener(args)
        }
    }
}