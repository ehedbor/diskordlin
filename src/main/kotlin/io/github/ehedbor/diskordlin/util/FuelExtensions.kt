/*
 * MIT License
 *
 * Copyright (c) 2017-2018 Evan Hedbor
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.ehedbor.diskordlin.util

import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.core.*
import com.github.kittinunf.result.Result

fun Request.body(obj: Any): Request {
    body(Klaxon().toJsonString(obj))
    return this
}

inline fun <reified T : Any> Request.responseObject(noinline handler: (Request, Response, Result<T, FuelError>) -> Unit): Request {
    return response(jsonDeserializerOf(), handler)
}

inline fun <reified T : Any> Request.responseObject(handler: Handler<T>): Request {
    return response(jsonDeserializerOf(), handler)
}

inline fun <reified T : Any> Request.responseObject(): Triple<Request, Response, Result<T, FuelError>> {
    return response(jsonDeserializerOf())
}


inline fun <reified T : Any> jsonDeserializerOf() = object : ResponseDeserializable<T> {
    override fun deserialize(content: String): T? {
        return Klaxon().parse<T>(content)
    }
}