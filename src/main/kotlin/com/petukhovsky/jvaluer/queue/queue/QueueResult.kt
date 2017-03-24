package com.petukhovsky.jvaluer.queue.queue

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by Arthur Petukhovsky on 3/24/2017.
 */

interface QueueResultRepository: MongoRepository<QueueResult, String> {

}
//TODO: more fields
data class QueueResult(
        @Id var id: String? = null
)