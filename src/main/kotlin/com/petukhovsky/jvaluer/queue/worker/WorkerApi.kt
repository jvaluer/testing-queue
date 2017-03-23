package com.petukhovsky.jvaluer.queue.worker

import com.petukhovsky.jvaluer.queue.tools.QueryError
import com.petukhovsky.jvaluer.queue.tools.QueryStatus
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

@RestController
class WorkerApi @Autowired constructor(
        val service: WorkerService
) {
    @RequestMapping("/worker/auth")
    fun auth(key: String, rand: String, sig: String): WorkerAuthResponse {
        return service.auth(key, rand, sig)
    }
}

data class WorkerAuthResponse(
        val status: QueryStatus,
        val error: QueryError?,
        val auth: WorkerAuth?
)