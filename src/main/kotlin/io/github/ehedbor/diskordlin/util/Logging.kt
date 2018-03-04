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

import org.slf4j.LoggerFactory
import org.slf4j.MarkerFactory

interface Logger {
    private val logger: org.slf4j.Logger
        get() = LoggerFactory.getLogger(this::class.java)

    fun trace(format: String, vararg args: Any) = logger.trace(format, args)
    fun trace(msg: String, throwable: Throwable) = logger.trace(msg, throwable)

    fun info(format: String, vararg args: Any) = logger.info(format, *args)
    fun info(msg: String, throwable: Throwable) = logger.info(msg, throwable)

    fun warn(format: String, vararg args: Any) = logger.warn(format, *args)
    fun warn(msg: String, throwable: Throwable) = logger.warn(msg, throwable)

    fun error(format: String, vararg args: Any) = logger.error(format, *args)
    fun error(msg: String, throwable: Throwable) = logger.error(msg, throwable)

    fun fatal(format: String, vararg args: Any) = logger.error(MarkerFactory.getMarker("FATAL"), format, *args)
    fun fatal(msg: String, throwable: Throwable) = logger.error(MarkerFactory.getMarker("FATAL"), msg, throwable)
}