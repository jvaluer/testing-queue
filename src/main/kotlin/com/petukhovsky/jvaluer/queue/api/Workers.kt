package com.petukhovsky.jvaluer.queue.api

import com.petukhovsky.jvaluer.queue.tools.QueryError
import com.petukhovsky.jvaluer.queue.tools.QueryStatus
import com.petukhovsky.jvaluer.queue.worker.WorkerInfo
import com.petukhovsky.jvaluer.queue.worker.WorkerRepository
import com.petukhovsky.jvaluer.queue.worker.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Arthur Petukhovsky on 3/23/2017.
 */
@RestController
@RequestMapping("/workers")
class Workers @Autowired constructor(
        val service: WorkerService,
        val repo: WorkerRepository
) {
    @RequestMapping("/all")
    fun all(): List<WorkerInfo> {
        return repo.findAll()!!
    }

    @RequestMapping("/add")
    fun add(@RequestParam("name") name: String): WorkerInfo {
        return service.createWorker(name)
    }

    @RequestMapping("/remove")
    fun remove(@RequestParam("id") id: String): RemoveResponse {
        if (!repo.exists(id)) {
            return RemoveResponse(
                    QueryStatus.FAIL,
                    QueryError("Id not found", 1, null)
            )
        }
        repo.delete(id)
        return RemoveResponse(
                QueryStatus.OK,
                null
        )
    }
}

data class RemoveResponse(
        val status: QueryStatus,
        val error: QueryError?
)