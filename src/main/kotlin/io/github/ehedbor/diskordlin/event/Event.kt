package io.github.ehedbor.diskordlin.event

/**
 * Represents an event that can be subscribed to and invoked.
 *
 * @param T The parameter that this event takes.
 */
class Event<T> {

    private val handlers: MutableMap<String?, MutableList<EventHandler<T>>> = mutableMapOf()

    @JvmName("put")
    operator fun plusAssign(handler: EventHandler<T>) = set(null, handler)

    @JvmName("put")
    operator fun plusAssign(namedHandler: Pair<String?, EventHandler<T>>) = set(namedHandler.first, namedHandler.second)

    @JvmName("put")
    operator fun set(eventName: String?, eventHandler: EventHandler<T>) {
        if (handlers[eventName] == null) {
            handlers[eventName] = mutableListOf()
        }
        handlers[eventName]!! += eventHandler
    }

    /**
     * Removes all event associated with this identifier.
     */
    @JvmName("remove")
    operator fun minusAssign(eventName: String?) {
        handlers.remove(eventName)
    }

    @JvmName("handle")
    operator fun invoke(parameter: T) {
        for ((_, handlerList) in handlers) {
            handlerList.forEach { it(parameter) }
        }
    }
}