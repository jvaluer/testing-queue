package com.petukhovsky.jvaluer.queue.queue

import com.petukhovsky.jvaluer.queue.tools.Data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Service
import java.time.Instant

/**
 * Created by Arthur Petukhovsky on 3/23/2017.
 */
@Service
class QueueService @Autowired constructor(
        val repo: QueueRepository
) {
    private fun calcIndex(): Int {
        return (repo.findFirstByOrderByIndexDesc()?.index ?: 0) + 1
    }

    private fun findUncompleted(): List<QueueElement> {
        return repo.findUncompletedElements()
    }
}