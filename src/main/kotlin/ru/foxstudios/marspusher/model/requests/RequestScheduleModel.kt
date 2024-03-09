package ru.foxstudios.marspusher.model.requests

data class RequestScheduleModel(
    var uuid: String = "",
    var speed: String = "",
    var fromD: String = "",
    var toD: String = "",
    var sended: Boolean = false
)
