package io.github.ehedbor.diskordlin.testapps

import io.github.ehedbor.diskordlin.Diskordlin
import io.github.ehedbor.diskordlin.client.ClientType
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

private const val TOKEN = "MzkxMzYyOTQyNDIwOTEwMDgw.DTwgnw.wupFapxhzvuPsi-DIGRRiGWYaHg"

fun main(args: Array<String>) = runBlocking {
    Diskordlin.loginAsync(TOKEN, ClientType.BOT).await()

    delay(Int.MAX_VALUE)
}