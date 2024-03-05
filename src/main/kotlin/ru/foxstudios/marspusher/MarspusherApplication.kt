package ru.foxstudios.marspusher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
class MarspusherApplication

fun main(args: Array<String>) {
    runApplication<MarspusherApplication>(*args)
}
