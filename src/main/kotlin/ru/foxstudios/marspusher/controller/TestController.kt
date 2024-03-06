package ru.foxstudios.marspusher.controller

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.foxstudios.marspusher.model.requests.DataModelRequest
import ru.foxstudios.marspusher.model.responces.DataToQueueResponce
import ru.foxstudios.marspusher.service.MessageService

@RestController
@RequestMapping("/data")
class TestController(@Autowired rabbitTemplate: RabbitTemplate) {
    var message: MessageService = MessageService(rabbitTemplate)
    @PostMapping("/add")
    fun addDataToQueue(@ModelAttribute body: DataModelRequest): DataToQueueResponce {
        return message.addDataToQueue(body)
    }

    @GetMapping("/history")
    fun history() : List<DataModelRequest>?{
        return message.history()
    }
}
