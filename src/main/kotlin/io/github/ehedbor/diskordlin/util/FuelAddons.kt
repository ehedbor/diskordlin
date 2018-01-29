@file:Suppress("unused")

package io.github.ehedbor.diskordlin.util

import com.github.kittinunf.fuel.core.Request

fun Request.addHeaders(vararg headers: Pair<String, String>): Request {
    headers.forEach { this.headers += it }
    return this
}

fun Request.addHeaders(headers: Map<String, String>): Request {
    this.headers += headers
    return this
}

// Basically the contents of com.github.kittinunf.fuel.gson.FuelGsonKt but adapted for Klaxon.
// Credit goes to the creator of that file, ihor_kucherenko (https://github.com/KucherenkoIhor)

//inline fun <reified T : Any> Request.responseObject(noinline handler: (Request, Response, Result<T, FuelError>) -> Unit)
//    = response(klaxonDeserializerOf(), handler)
//
//inline fun <reified T : Any> Request.responseObject(handler: Handler<T>) = response(klaxonDeserializerOf(), handler)
//
//inline fun <reified T : Any> Request.responseObject() = response(klaxonDeserializerOf<T>())
//
//inline fun <reified T : Any> klaxonDeserializerOf() = object : ResponseDeserializable<T> {
//    override fun deserialize(reader: Reader): T? = Klaxon().parse<T>(reader)
//}