package com.thoughtworks.techtalk.rabbitmq.config


import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableRabbit
class RabbitConfig {
    // Exchange OrderCreatedEvent
    @Bean("OrderCreatedEvent")
    fun orderCreatedEventDirectExchange(): Exchange = ExchangeBuilder.directExchange("OrderCreatedEvent").build()

    // Queue `order.create.queue` and binding
    @Bean("order.create.queue")
    fun orderCreateQueue(): Queue = QueueBuilder.durable("order.create.queue")
        .build()
    @Bean
    fun orderCreateQueueBinding(
        @Qualifier("order.create.queue") queue: Queue,
        @Qualifier("OrderCreatedEvent") exchange: Exchange
    ): Binding =
        BindingBuilder.bind(queue).to(exchange).with("order.create").noargs()

    // Queue `order.create.log.queue` and binding
    @Bean("order.create.log.queue")
    fun orderCreateLogQueue(): Queue = QueueBuilder.durable("order.create.log.queue")
        .build()
    @Bean
    fun orderCreateLogQueueBinding(
        @Qualifier("order.create.log.queue") queue: Queue,
        @Qualifier("OrderCreatedEvent") exchange: Exchange
    ): Binding =
        BindingBuilder.bind(queue).to(exchange).with("order.create.log").noargs()
}
