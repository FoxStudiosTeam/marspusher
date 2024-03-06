package ru.foxstudios.marspusher.config

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMQConfig(
    @Value("marspusher.rabbitmq.login") var password: String,
    @Value("marspusher.rabbitmq.password") var login: String
) {
    @Bean
    fun messageQueue(): Queue {
        return Queue("mars-queue")
    }

    @Bean
    fun messageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val cachingConnectionFactory = CachingConnectionFactory("mars-queue-service")
        println("PASSWORD - $password")
        println("LOGIN - $login")
        cachingConnectionFactory.port = 5672
        cachingConnectionFactory.username = login
        cachingConnectionFactory.setPassword(password)
        cachingConnectionFactory.virtualHost = "cpp"
        return cachingConnectionFactory
    }


    @Bean
    fun rabbitTemplate(): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory())
        rabbitTemplate.messageConverter = messageConverter()
        return rabbitTemplate
    }
}
