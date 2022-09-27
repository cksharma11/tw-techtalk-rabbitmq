package com.thoughtworks.techtalk.rabbitmq.consumer

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableRabbit
class RabbitReceiverBindings {
    @Bean("queue-1")
    fun directExchangeQueue(): Queue = QueueBuilder.durable("queue-1")
        .build()

    @Bean("exchange-1")
    fun customerCreateExchange(): Exchange = ExchangeBuilder.topicExchange("exchange-1").build()
    @Bean
    fun directExchangeBinding(
        @Qualifier("queue-1") queue: Queue,
        @Qualifier("exchange-1") exchange: Exchange
    ): Binding =
        BindingBuilder.bind(queue).to(exchange).with("routing-key-1").noargs()
}
