package ru.foxstudios.marspusher.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.foxstudios.marspusher.dto.DataModel
import ru.foxstudios.marspusher.model.requests.DataModelRequest

import ru.foxstudios.marspusher.model.responces.DataToQueueResponce

@Service
class MessageService(@Autowired val rabbitTemplate: RabbitTemplate) {
    fun addDataToQueue(model: DataModelRequest): DataToQueueResponce {
        val mapper = ObjectMapper().registerKotlinModule()
        val message = DataModel(model.name, model.commentary, mapper.readValue(model.file.bytes, String::class.java))
        rabbitTemplate.convertAndSend("mars", message)
        return DataToQueueResponce(0, "ok")
    }
}
