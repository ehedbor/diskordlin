package io.github.ehedbor.diskordlin.event

/**
 * Contains the arguments for an event.
 */
class EventArgs(vararg startingArgs: Pair<String, Any?>) {
    private val args = mutableMapOf<String, Any?>()

    init {
        args.putAll(startingArgs)
    }

    operator fun set(name: String, arg: Any) { args[name] = arg }

    operator fun get(name: String) = args.getOrDefault(name, null)
}