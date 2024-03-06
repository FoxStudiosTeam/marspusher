package ru.foxstudios.marspusher.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.commons.codec.binary.Base64
import org.apache.commons.io.FilenameUtils
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.foxstudios.marspusher.dto.DataModel
import ru.foxstudios.marspusher.model.requests.DataModelRequest

import ru.foxstudios.marspusher.model.responces.DataToQueueResponce

@Service
class MessageService(@Autowired val rabbitTemplate: RabbitTemplate) {
    fun addDataToQueue(model: DataModelRequest): DataToQueueResponce {
        val base64 = Base64.encodeBase64String(model.file.bytes)
        val data = DataModel(model.name, model.commentary, base64, FilenameUtils.getExtension(model.file.originalFilename))
        val mapper = ObjectMapper().registerKotlinModule()
        val message = mapper.writeValueAsString(data)//test
        rabbitTemplate.convertAndSend("mars-queue", message)
        return DataToQueueResponce(0, rabbitTemplate.connectionFactory.host.toString())
    }
}
