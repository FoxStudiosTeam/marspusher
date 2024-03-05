package ru.foxstudios.marspusher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MarspusherApplication

fun main(args: Array<String>) {
    runApplication<MarspusherApplication>(*args)
}
