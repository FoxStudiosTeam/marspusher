package ru.foxstudios.marspusher.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clown")
class TestController {
    @GetMapping("/test")
    fun test() : String{
        return "work!!"
    }
}
