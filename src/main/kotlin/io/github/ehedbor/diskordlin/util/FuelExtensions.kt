package io.github.ehedbor.diskordlin.util

import com.github.kittinunf.fuel.core.Request
import com.github.salomonbrys.kotson.registerTypeAdapter
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import io.github.ehedbor.diskordlin.entities.Snowflake

fun Request.body(json: JsonElement): Request {
    val gson = GsonBuilder()
        .registerTypeAdapter(Snowflake.serializer)
        .registerTypeAdapter(Snowflake.deserializer)
        .create()
    this.body(gson.toJson(json))
    return this
}