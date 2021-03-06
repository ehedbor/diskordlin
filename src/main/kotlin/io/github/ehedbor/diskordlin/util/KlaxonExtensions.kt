package io.github.ehedbor.diskordlin.util

import com.beust.klaxon.Klaxon
import io.github.ehedbor.diskordlin.entities.Snowflake
import io.github.ehedbor.diskordlin.entities.gateway.Payload

/**
 * Adds the custom converters for [Payload] and [Snowflake] to this object.
 */
fun Klaxon.withDefaultConverters(): Klaxon {
    converter(Payload)
    converter(Snowflake)
    return this
}