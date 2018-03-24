package io.github.ehedbor.diskordlin.util

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import java.io.StringReader

fun Klaxon.toJsonObject(obj: Any): JsonObject {
    return this.parseJsonObject(
        StringReader(this.toJsonString(obj))
    )
}