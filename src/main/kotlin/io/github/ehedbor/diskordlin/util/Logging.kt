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