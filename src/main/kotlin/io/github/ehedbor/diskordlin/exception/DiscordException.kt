package io.github.ehedbor.diskordlin.exception

/**
 * A [RuntimeException] used within the Diskordlin API.
 */
@Suppress("unused")
class DiscordException : RuntimeException {

    @JvmOverloads
    constructor(message: String? = null, cause: Throwable? = null) : super(message, cause)

    constructor(message: String? = null, cause: Throwable? = null, enableSuppression: Boolean, writableStackTrace: Boolean)
        : super(message, cause, enableSuppression, writableStackTrace)
}