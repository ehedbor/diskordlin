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

package io.github.ehedbor.diskordlin.entities.user

import com.beust.klaxon.Json
import io.github.ehedbor.diskordlin.entities.Snowflake

data class Activity(
    val name: String,
    val type: Int,
    val url: String? = null,
    val timestamps: Timestamps? = null,
    @Json("application_id") val applicationId: Snowflake? = null,
    val details: String? = null,
    val state: String? = null,
    val party: Party? = null,
    val assets: Assets? = null
)

object ActivityType {
    const val GAME = 0
    const val STREAMING = 1
    const val LISTENING = 2
}

data class Timestamps(
    val start: Long? = null,
    val end: Long? = null
)

data class Party(
    val id: Snowflake? = null,
    val size: List<Int> = listOf()
)

data class Assets(
    @Json("large_image") val largeImage: String? = null,
    @Json("large_text")  val largeText: String? = null,
    @Json("small_image") val smallImage: String? = null,
    @Json("small_text")  val smallText: String? = null
)