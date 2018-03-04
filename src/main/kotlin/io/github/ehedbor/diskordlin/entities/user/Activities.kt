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

import io.github.ehedbor.diskordlin.entities.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName as Name

@Serializable
data class Activity(
    val name: String,
    val type: Int,
    @Optional val url: String? = null,
    @Optional val timestamps: Timestamps? = null,
    @Optional @Name("application_id") val applicationId: Snowflake? = null,
    @Optional val details: String? = null,
    @Optional val state: String? = null,
    @Optional val party: Party? = null,
    @Optional val assets: Assets? = null
)

object ActivityType {
    const val GAME = 0
    const val STREAMING = 1
    const val LISTENING = 2
}

@Serializable
data class Timestamps(
    @Optional val start: Long? = null,
    @Optional val end: Long? = null
)

@Serializable
data class Party(
    @Optional val id: Snowflake? = null,
    @Optional val size: List<Int> = listOf()
)

@Serializable
data class Assets(
    @Optional @Name("large_image") val largeImage: String? = null,
    @Optional @Name("large_text")  val largeText: String? = null,
    @Optional @Name("small_image") val smallImage: String? = null,
    @Optional @Name("small_text")  val smallText: String? = null
)