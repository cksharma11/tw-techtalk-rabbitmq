package com.thoughtworks.techtalk.rabbitmq.producer

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RabbitProduceController(private val rabbitTemplate: RabbitTemplate) {
    @PostMapping("/order/create")
    fun sendOrderCreateMessage(): ResponseEntity<String> {
        val message = "Order created"
        rabbitTemplate.convertAndSend("OrderCreatedEvent", "order.create", message)
        return ResponseEntity.ok("Message sent successfully: $message")
    }

    @PostMapping("/order/log")
    fun sendOrderCreateLogMessage(): ResponseEntity<String> {
        val message = "Order created log"
        rabbitTemplate.convertAndSend("OrderCreatedEvent", "order.create.log", message)
        return ResponseEntity.ok("Message sent successfully: $message")
    }
}
