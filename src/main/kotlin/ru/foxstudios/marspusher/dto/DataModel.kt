package ru.foxstudios.marspusher.dto

import org.springframework.web.multipart.MultipartFile

data class DataModel(var name:String,var commentary:String,var file:MultipartFile)
