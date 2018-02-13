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