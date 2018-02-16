package io.github.ehedbor.diskordlin.client

/**
 * The possible types of Discord client. Currently only bot clients are supported.
 */
enum class ClientType {

    /**
     * Signifies that this client is a bot.
     */
    BOT,

    /**
     * Signifies that the client is an authenticated user.
     */
    OAUTH2_USER
}
