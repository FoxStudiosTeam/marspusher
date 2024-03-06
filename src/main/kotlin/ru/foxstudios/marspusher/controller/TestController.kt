package ru.foxstudios.marspusher.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.foxstudios.marspusher.dto.DataModel

@RestController
@RequestMapping("/data")
class TestController {
    @PostMapping("/add")
    fun addDataToQueue(@ModelAttribute body: DataModel) {
        //comment
    }

    @GetMapping("test")
    fun test(): String {
        return "test"
    }
}
