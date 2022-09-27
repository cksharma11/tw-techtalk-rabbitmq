package com.thoughtworks.techtalk.rabbitmq.config

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ConfigController(private val rabbitTemplate: RabbitTemplate) {
    @PostMapping("/status")
    fun status(): ResponseEntity<String> {
        return ResponseEntity.ok("Running")
    }
}
