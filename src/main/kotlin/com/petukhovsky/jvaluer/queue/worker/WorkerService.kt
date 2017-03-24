package com.petukhovsky.jvaluer.queue.worker

import com.fasterxml.jackson.annotation.JsonInclude
import com.petukhovsky.jvaluer.queue.tools.QueryError
import com.petukhovsky.jvaluer.queue.tools.QueryStatus
import com.petukhovsky.jvaluer.queue.tools.generateToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Arthur Petukhovsky on 3/22/2017.
 */
@Service
class WorkerService @Autowired constructor(
        val repo: WorkerRepository,
        val verifier: WorkerSignatureVerifier
){

    val tokens = ConcurrentHashMap<String, String>()

    fun getWorkerByToken(token: String): WorkerInfo? {
        return repo.findOneByKey(tokens[token] ?: return null)?.takeIf { it.acceptableToken == token }
    }

    fun auth(key: String, rand: String, sig: String): WorkerAuthResponse {
        val info = repo.findOneByKey(key)
                ?: return WorkerAuthResponse(
                            QueryStatus.FAIL,
                            QueryError("Key is not found", 1, null),
                            null
                )

        if (!verifier.verify(rand, sig, info.secret)) {
            return WorkerAuthResponse(
                    QueryStatus.FAIL,
                    QueryError("Incorrect signature", 2, null),
                    null
            )
        }

        val token = generateToken()
        tokens[token] = key

        repo.save(info.also { it.acceptableToken = token })

        return WorkerAuthResponse(
                QueryStatus.OK,
                null,
                WorkerAuth(token)
        )
    }

    fun createWorker(name: String): WorkerInfo {
        return repo.save(
                WorkerInfo(
                        null,
                        generateToken(15),
                        generateToken(35),
                        name,
                        null
                )
        )
    }
}

interface WorkerRepository: MongoRepository<WorkerInfo, String> {
    fun findOneByKey(key: String): WorkerInfo?
}

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
data class WorkerInfo(
        @Id var id: String? = null,
        @Indexed(unique = true) var key: String = "",
        var secret: String = "",
        var name: String = "",
        @Indexed(unique = true, sparse = true) var acceptableToken: String? = null
)