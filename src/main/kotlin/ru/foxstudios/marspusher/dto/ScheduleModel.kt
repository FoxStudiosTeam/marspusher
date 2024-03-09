package ru.foxstudios.marspusher.dto

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "schedule")
data class ScheduleModel(
    @Id var uuid: String = "",
    var speed: Double = 0.0,
    var fromD: LocalDateTime = LocalDateTime.now(),
    var toD: LocalDateTime = LocalDateTime.now()
)
