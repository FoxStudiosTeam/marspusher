package ru.foxstudios.marspusher.service

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.foxstudios.marspusher.dto.DataModel
import ru.foxstudios.marspusher.model.responces.DataToQueueResponce

@Service
class MessageService(@Autowired val rabbitTemplate: RabbitTemplate) {
    fun addDataToQueue(model: DataModel): DataToQueueResponce {

        rabbitTemplate.convertAndSend("mars", model)
        return DataToQueueResponce(0, "ok")
    }
}
