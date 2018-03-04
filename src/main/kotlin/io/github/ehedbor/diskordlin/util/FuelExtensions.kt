package io.github.ehedbor.diskordlin.util

import com.github.kittinunf.fuel.core.*
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.JSON

fun Request.body(obj: Any): Request {
    body(JSON.stringify(obj))
    return this
}

inline fun <reified T : Any> Request.responseObject(noinline handler: (Request, Response, Result<T, FuelError>) -> Unit)
    = response(jsonDeserializerOf(), handler)

inline fun <reified T : Any> Request.responseObject(handler: Handler<T>) = response(jsonDeserializerOf(), handler)

inline fun <reified T : Any> Request.responseObject() = response(jsonDeserializerOf<T>())

inline fun <reified T : Any> jsonDeserializerOf() = object : ResponseDeserializable<T> {
    override fun deserialize(content: String): T = JSON.parse(content)
}