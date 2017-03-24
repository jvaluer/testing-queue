package com.petukhovsky.jvaluer.queue.queue

import com.petukhovsky.jvaluer.queue.tools.Data
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by Arthur Petukhovsky on 3/24/2017.
 */

interface QueueTaskRepository: MongoRepository<QueueTask, String> {

}

data class QueueTask(
        @Id var id: String? = null,
        var type: QueueTaskType? = null,
        var source: SourceInfo? = null,
        var packageInfo: PackageInfo? = null
)

enum class QueueTaskType {
    testPackaged
}

data class SourceInfo(
        var src: Data = Data.string(""),
        var type: SourceType = SourceType.code,
        var codeLang: String? = null
)

enum class SourceType {
    code
}

data class PackageInfo(
        var id: String = "",
        var accessUrl: String = ""
)