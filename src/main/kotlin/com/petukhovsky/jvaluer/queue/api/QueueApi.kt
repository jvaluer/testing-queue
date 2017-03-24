package com.petukhovsky.jvaluer.queue.api

import com.petukhovsky.jvaluer.queue.queue.QueueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Arthur Petukhovsky on 3/23/2017.
 */
@RestController
class QueueApi @Autowired constructor(
    val service: QueueService
){
    fun push() {

    }
}