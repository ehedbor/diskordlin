package io.github.ehedbor.diskordlin.event

    /**
     * Represents a method that is called by [Event]s.
     */
typealias EventListener<T> = (T) -> Unit