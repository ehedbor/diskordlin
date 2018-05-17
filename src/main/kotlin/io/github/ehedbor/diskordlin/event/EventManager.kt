package io.github.ehedbor.diskordlin.event

internal class EventManager {
    private val allEvents: MutableMap<String, Event> = mutableMapOf()

    fun register(name: String, listener: EventListener) {
        if (allEvents[name] == null) {
            allEvents[name] = Event()
        }
        allEvents[name]!! += listener
    }

    internal fun invokeEvent(name: String, args: EventArgs) {
        allEvents[name]?.invoke(args)
    }
}