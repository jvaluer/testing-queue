package com.petukhovsky.jvaluer.queue.worker

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
* Created by Arthur Petukhovsky on 3/22/2017.
*/

@RestController("/worker")
class WorkerApi @Autowired constructor(
        val service: WorkerService
) {
    @RequestMapping("/auth")
    fun auth(key: String, rand: String, sig: String): WorkerAuthResponse {
        return service.auth(key, rand, sig)
    }
}