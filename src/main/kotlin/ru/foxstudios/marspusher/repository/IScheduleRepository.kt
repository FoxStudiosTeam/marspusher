package ru.foxstudios.marspusher.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.foxstudios.marspusher.dto.ScheduleModel
import java.time.LocalDateTime

interface IScheduleRepository : JpaRepository<ScheduleModel, String> {
    fun findByFromDBeforeAndToDAfter(fromD: LocalDateTime, toD: LocalDateTime) : ScheduleModel
}
