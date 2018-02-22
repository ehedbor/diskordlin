package io.github.ehedbor.diskordlin

import java.util.*

/**
 * Loads build-specific project properties.
 */
object BuildInfo {
    private val properties by lazy {
        val p = Properties()
        val input = this::class.java.classLoader.getResourceAsStream("build-info.properties")
        input.use {
            p.load(it)
        }
        p
    }

    val name = properties["name"] as String
    val version = properties["version"] as String
    val url = properties["url"] as String
}