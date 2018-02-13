package io.github.ehedbor.diskordlin.event

import io.github.ehedbor.diskordlin.model.chat.Message
import io.github.ehedbor.diskordlin.model.gateway.event.ReadyEvent

object Events {
    val ready = Event<ReadyEvent>()
    val messageCreate = Event<Message>()
}