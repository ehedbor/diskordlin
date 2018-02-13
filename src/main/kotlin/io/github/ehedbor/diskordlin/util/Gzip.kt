package io.github.ehedbor.diskordlin.util

import java.io.ByteArrayOutputStream
import java.util.zip.Deflater
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream
import java.util.zip.Inflater
import kotlin.text.Charsets.UTF_8


fun compressGZip(content: String): ByteArray {
    val bos = ByteArrayOutputStream()
    GZIPOutputStream(bos).bufferedWriter(UTF_8).use { it.write(content) }
    bos.close()
    return bos.toByteArray()
}

fun decompressGZip(content: ByteArray): String =
    GZIPInputStream(content.inputStream()).bufferedReader(UTF_8).use { it.readText() }

fun compressZLib(data: String): ByteArray {
    @Suppress("NAME_SHADOWING")
    val data = data.toByteArray()

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