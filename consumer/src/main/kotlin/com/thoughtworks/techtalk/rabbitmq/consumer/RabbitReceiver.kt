package com.thoughtworks.techtalk.rabbitmq.consumer

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitReceiver {

    @RabbitListener(queues = ["order.create.queue"])
    fun orderCreateListener(message: String) {
        println("=========================")
        println("message received on consumer 1 - $message")
        println("=========================")
    }

    @RabbitListener(queues = ["order.create.log.queue"])
    fun orderCreateLogListener(message: String) {
        println("=========================")
        println("message received on consumer 2 - $message")
        println("=========================")
    }

}
