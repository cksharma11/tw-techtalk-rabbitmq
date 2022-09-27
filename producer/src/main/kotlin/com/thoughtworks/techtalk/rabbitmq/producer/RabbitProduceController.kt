package com.thoughtworks.techtalk.rabbitmq.producer

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RabbitProduceController(private val rabbitTemplate: RabbitTemplate) {
    @PostMapping("/person/{name}")
    fun sendMessage(@PathVariable name: String): ResponseEntity<String> {
        rabbitTemplate.convertAndSend("exchange-1", "routing-key-1", name)
        return ResponseEntity.ok(name)
    }
}
