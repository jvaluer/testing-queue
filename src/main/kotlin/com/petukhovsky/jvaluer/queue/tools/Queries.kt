package com.petukhovsky.jvaluer.queue.tools

/**
 * Created by Arthur Petukhovsky on 3/22/2017.
 */

enum class QueryStatus {
    OK, FAIL
}

class QueryError(
        val message: String?,
        val code: Long,
        val info: Map<String, String>?
)