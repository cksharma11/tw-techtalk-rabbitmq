package com.thoughtworks.techtalk.rabbitmq.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableRabbit
class RabbitConfig {
    // Topic exchange
    @Bean("topic.order.event")
    fun topicOrderExchange(): Exchange = ExchangeBuilder.topicExchange("topic.order.event").build()

    @Bean("order.create.queue")
    fun createOrderQueue(): Queue = QueueBuilder.durable("order.create.queue")
        .build()

    @Bean
    fun createOrderQueueBinding(
        @Qualifier("order.create.queue") queue: Queue,
        @Qualifier("topic.order.event") exchange: Exchange
    ): Binding =
        BindingBuilder.bind(queue).to(exchange).with("order.create").noargs()

    @Bean("order.event.queue")
    fun orderEventQueue(): Queue = QueueBuilder.durable("order.event.queue")
        .build()

    @Bean
    fun orderEventQueueBinding(
        @Qualifier("order.event.queue") queue: Queue,
        @Qualifier("topic.order.event") exchange: Exchange
    ): Binding =
        BindingBuilder.bind(queue).to(exchange).with("order.#").noargs()
}
