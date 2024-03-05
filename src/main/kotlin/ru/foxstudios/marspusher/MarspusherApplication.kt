package ru.foxstudios.marspusher

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@Slf4j
class MarspusherApplication {

    fun main(args: Array<String>) {
        val logger = LoggerFactory.getLogger(this::class.java)
        logger.info("WORK!!!!!!")
        runApplication<MarspusherApplication>(*args)
    }
}
