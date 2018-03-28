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

package io.github.ehedbor.diskordlin.entities.channel

import com.beust.klaxon.Json

// This is the ideal file body. You may not like it,
// but this is what peak performance looks like.

@Suppress("ArrayInDataClass")
data class Embed(
    val title: String? = null,
    val type: String? = null,
    val description: String? = null,
    val url: String? = null,
    val timestamp: String? = null,
    val color: Int? = null,
    val footer: EmbedFooter? = null,
    val image: EmbedImage? = null,
    val thumbnail: EmbedThumbnail? = null,
    val video: EmbedVideo? = null,
    val provider: EmbedProvider? = null,
    val author: EmbedAuthor? = null,
    val fields: List<EmbedField> = emptyList()
)

data class EmbedFooter(
    val text: String,
    @Json("icon_url") val iconUrl: String? = null,
    @Json("proxy_icon_url")  val proxyIconUrl: String? = null
)

data class EmbedImage(
    val url: String,
    @Json("proxy_url") val proxyUrl: String? = null,
    val height: Int? = null,
    val width: Int? = null
)

data class EmbedThumbnail(
    val url: String? = null,
    @Json("proxy_url") val proxyUrl: String? = null,
    val height: Int? = null,
    val width: Int? = null
)

data class EmbedVideo(
    val url: String? = null,
    val height: Int? = null,
    val width: Int? = null
)

data class EmbedProvider(
    val name: String? = null,
    val url: String? = null
)

data class EmbedAuthor(
    val name: String? = null,
    val url: String? = null,
    @Json("icon_url") val iconUrl: String? = null,
    @Json("proxy_icon_url") val proxyIconUrl: String? = null
)

data class EmbedField(
    val name: String? = null,
    val value: String? = null,
    val inline: Boolean? = null
)