package io.github.ehedbor.diskordlin.event

import io.github.ehedbor.diskordlin.entities.channel.Message
import io.github.ehedbor.diskordlin.entities.gateway.event.ReadyEvent

object Events {
    val ready = Event<ReadyEvent>()
    val messageCreate = Event<Message>()
}