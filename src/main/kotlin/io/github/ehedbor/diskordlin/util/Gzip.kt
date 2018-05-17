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

import java.io.ByteArrayOutputStream
import java.util.zip.Deflater
import java.util.zip.Inflater

/**
 * Compresses a byte array with the ZLib format.
 */
fun compressZLib(data: ByteArray): ByteArray {
    val deflater = Deflater()
    deflater.setInput(data)
    deflater.finish()

    val buffer = ByteArray(1024)
    val outputStream = ByteArrayOutputStream(data.size)
    outputStream.use {
        while (!deflater.finished()) {
            val count = deflater.deflate(buffer)
            outputStream.write(buffer, 0, count)
        }
    }
    return outputStream.toByteArray()
}

/**
 * Compresses a String with the ZLib format.
 */
fun compressZLib(data: String) = compressZLib(data.toByteArray())

/**
 * Decompresses a byte array encoded in the ZLib format.
 */
fun decompressZLib(data: ByteArray): String {
    val inflater = Inflater()
    inflater.setInput(data)

    val buffer = ByteArray(1024)
    val outputStream = ByteArrayOutputStream(data.size)
    outputStream.use {
        while (!inflater.finished()) {
            val count = inflater.inflate(buffer)
            outputStream.write(buffer, 0, count)
        }
    }
    return outputStream.toByteArray().toString()
}