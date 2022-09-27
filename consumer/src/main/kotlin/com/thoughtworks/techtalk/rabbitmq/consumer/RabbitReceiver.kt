package com.thoughtworks.techtalk.rabbitmq.consumer

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitReceiver {

    @RabbitListener(queues = ["queue-1"])
    fun directExchangeListener(message: String) {
        println("=========================")
        println("message received on queue-1 - $message")
        println("=========================")
    }
}
