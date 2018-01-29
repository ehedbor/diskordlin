package io.github.ehedbor.diskordlin.websocket

import javax.websocket.ClientEndpointConfig

internal class HeaderConfigurator : ClientEndpointConfig.Configurator() {

    companion object {
        internal val headers: MutableMap<String, String> = mutableMapOf()
    }

    /**
     * Adds the header values.
     */
    override fun beforeRequest(headers: MutableMap<String, MutableList<String>>) {
        HeaderConfigurator.headers.forEach { key, value ->
            headers[key] = mutableListOf(value)
        }
    }
}