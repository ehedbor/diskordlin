package io.github.ehedbor.diskordlin.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MarkerFactory

@Suppress("unused")
inline val <reified T> T.logger: Logger
    get() = LoggerFactory.getLogger(T::class.java)

inline fun <reified T> T.trace(msg: String) = logger.trace(msg)
inline fun <reified T> T.trace(format: String, vararg args: Any) = logger.trace(format, *args)
inline fun <reified T> T.trace(msg: String, throwable: Throwable) = logger.trace(msg, throwable)

inline fun <reified T> T.info(msg: String) = logger.info(msg)
inline fun <reified T> T.info(format: String, vararg args: Any) = logger.info(format, *args)
inline fun <reified T> T.info(msg: String, throwable: Throwable) = logger.info(msg, throwable)

inline fun <reified T> T.warn(msg: String) = logger.warn(msg)
inline fun <reified T> T.warn(format: String, vararg args: Any) = logger.warn(format, *args)
inline fun <reified T> T.warn(msg: String, throwable: Throwable) = logger.warn(msg, throwable)

inline fun <reified T> T.error(msg: String) = logger.error(msg)
inline fun <reified T> T.error(format: String, vararg args: Any) = logger.error(format, *args)
inline fun <reified T> T.error(msg: String, throwable: Throwable) = logger.error(msg, throwable)

inline fun <reified T> T.fatal(msg: String) = logger.error(MarkerFactory.getMarker("FATAL"), msg)
inline fun <reified T> T.fatal(format: String, vararg args: Any) = logger.error(MarkerFactory.getMarker("FATAL"), format, *args)
inline fun <reified T> T.fatal(msg: String, throwable: Throwable) = logger.error(MarkerFactory.getMarker("FATAL"), msg, throwable)