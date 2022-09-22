package com.thoughtworks.techtalk.rabbitmq.producer

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RabbitProduceController(private val rabbitTemplate: RabbitTemplate) {
    @PostMapping("/person/{name}")
    fun postPerson(@PathVariable name: String): ResponseEntity<String> {
        rabbitTemplate.convertAndSend("hello", name)
        return ResponseEntity.ok(name)
    }
}
