package ru.foxstudios.marspusher.config

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {
    @Bean
    fun messageQueue() : Queue{
        return Queue("mars-queue")
    }
}
