package ru.foxstudios.marspusher.config

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.Connection
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMQConfig(
    @Value("\${marspusher.rabbitmq.login}") var login: String,
    @Value("\${marspusher.rabbitmq.password}") var password: String
) {
    @Bean
    fun messageQueue(): Queue {
        val connection: Connection = connectionFactory().createConnection()
        val channel = connection.createChannel(false)
        try{
            channel.queueDeclarePassive("mars-queue")
        }catch (e:Exception){
            channel.queueDeclare("mars-queue", true, false, false, null)
        }
        return Queue("mars-queue")
    }

    @Bean
    fun messageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val cachingConnectionFactory = CachingConnectionFactory("mars-queue-service")
        cachingConnectionFactory.port = 5672
        cachingConnectionFactory.username = login
        cachingConnectionFactory.setPassword(password)
        return cachingConnectionFactory
    }


    @Bean
    fun rabbitTemplate(): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory())
        rabbitTemplate.messageConverter = messageConverter()
        return rabbitTemplate
    }
}
