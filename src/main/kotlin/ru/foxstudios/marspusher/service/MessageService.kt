package ru.foxstudios.marspusher.service

import org.apache.commons.codec.binary.Base64
import org.apache.commons.io.FilenameUtils
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.foxstudios.marspusher.dto.DataModel
import ru.foxstudios.marspusher.dto.ScheduleModel
import ru.foxstudios.marspusher.model.requests.DataModelRequest
import ru.foxstudios.marspusher.model.requests.RequestSchedule
import ru.foxstudios.marspusher.model.requests.RequestScheduleModel
import ru.foxstudios.marspusher.model.responces.DataToQueueResponce
import ru.foxstudios.marspusher.repository.IScheduleRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Service
class MessageService(@Autowired val rabbitTemplate: RabbitTemplate, val repository: IScheduleRepository) {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    fun addDataToQueue(model: DataModelRequest): DataToQueueResponce {
        val base64 = Base64.encodeBase64String(model.file.bytes)
        rabbitTemplate.convertAndSend(
            "mars-queue",
            DataModel(model.name, model.commentary, base64, FilenameUtils.getExtension(model.file.originalFilename))
        )
        return DataToQueueResponce(0, rabbitTemplate.connectionFactory.host.toString())
    }

    fun history(): List<DataModelRequest>? {
        val list: List<DataModelRequest>? = null
        return list
    }

    fun addSchedule(body: RequestScheduleModel) {


        val modelToSave = ScheduleModel(
            UUID.randomUUID().toString(),
            body.speed.toDouble(),
            LocalDateTime.parse(body.fromD, formatter),
            LocalDateTime.parse(body.toD, formatter),
        )
        repository.save(modelToSave)
    }

    fun getSchedule(): ScheduleModel {
        val body = LocalDateTime.now().format(formatter)
        val date = LocalDateTime.parse(body, formatter)
        return repository.findByFromDBeforeAndToDAfter(date, date)
    }
}
