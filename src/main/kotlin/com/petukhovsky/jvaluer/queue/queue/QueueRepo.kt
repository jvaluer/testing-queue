package com.petukhovsky.jvaluer.queue.queue

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.time.Instant

/**
 * Created by Arthur Petukhovsky on 3/24/2017.
 */
interface QueueRepository: MongoRepository<QueueElement, String> {
    fun findFirstByOrderByIndexDesc(): QueueElement?

    @Query("{ 'info.status' : { \$ne : 2 } }")
    fun findUncompletedElements(): List<QueueElement>
}

data class QueueElement(
        @Id var id: String? = null,
        @Indexed(unique = true) var index: Int = 0,
        var queueTaskId: String = "",
        var resultId: String = "",
        var created: Instant = Instant.now(),
        var info: QueueInfo = QueueInfo()
)

data class QueueInfo(
        var updated: Instant = Instant.now(),
        var completed: Instant? = null,
        var status: Int = 0
)