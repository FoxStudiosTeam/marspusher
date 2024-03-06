package ru.foxstudios.marspusher.model.requests

import org.springframework.web.multipart.MultipartFile

data class DataModelRequest(var name:String, var commentary:String, var file:MultipartFile)
