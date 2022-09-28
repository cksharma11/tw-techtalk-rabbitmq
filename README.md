# tw-techtalk-rabbitmq

Demo repo for rabbit-mq session.

## How to run
1. Running rabbit-mq in local - 
docker-compose up </br></br>
Rabbit mq ui - http://localhost:15672 </br>
Username - guest</br>
Password - guest</br>

2. Run producer `cd producer && ./gradlew bootRun`
3. Publish a message using `POST http://localhost:8081/order/create` (this is needed because queues will only be created after the request to rabbit-mq)
4. Run consumer `cd consumer && ./gradlew bootRun`
5. See the logs in consumer service logs

Publishing messages - 
1. Generate CreateOrder event - POST http://localhost:8081/order/create
2. Generate OrderLog event - POST http://localhost:8081/order/log


## content

It has a working example for direct exchange

Exchange -

- OrderCreatedEvent

```kt
@Bean("OrderCreatedEvent")
fun orderCreatedEventDirectExchange(): Exchange = ExchangeBuilder.directExchange("OrderCreatedEvent").build()
```

Queues -
- order.create.queue
- order.create.log.queue

```kt
@Bean("order.create.queue")
fun orderCreateQueue(): Queue = QueueBuilder.durable("order.create.queue")
        .build()
```

```kt
@Bean("order.create.log.queue")
fun orderCreateLogQueue(): Queue = QueueBuilder.durable("order.create.log.queue")
        .build()
```

Bindings -

```kt
@Bean
fun orderCreateQueueBinding(
    @Qualifier("order.create.queue") queue: Queue,
    @Qualifier("OrderCreatedEvent") exchange: Exchange
): Binding =
    BindingBuilder.bind(queue).to(exchange).with("order.create").noargs()
```

```kt
@Bean
fun orderCreateLogQueueBinding(
    @Qualifier("order.create.log.queue") queue: Queue,
    @Qualifier("OrderCreatedEvent") exchange: Exchange
): Binding =
    BindingBuilder.bind(queue).to(exchange).with("order.create.log").noargs()
```
