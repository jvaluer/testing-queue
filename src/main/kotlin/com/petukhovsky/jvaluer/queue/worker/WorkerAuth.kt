package com.petukhovsky.jvaluer.queue.worker

import com.petukhovsky.jvaluer.queue.tools.QueryError
import com.petukhovsky.jvaluer.queue.tools.QueryStatus
import com.petukhovsky.jvaluer.queue.tools.toHexString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.security.MessageDigest

/**
 * Created by Arthur Petukhovsky on 3/22/2017.
 */

data class WorkerAuthResponse(
        val status: QueryStatus,
        val error: QueryError?,
        val auth: WorkerAuth?
)

data class WorkerAuth(
        val token: String
)

interface WorkerSignatureVerifier {
    fun verify(rand: String, sig: String, secret: String): Boolean
}

interface WorkerSignatureEncoder {
    fun encode(rand: String, secret: String): String
}

@Service
class DefaultSignatureVerifier @Autowired constructor(
    val encoder: WorkerSignatureEncoder
): WorkerSignatureVerifier {
    override fun verify(rand: String, sig: String, secret: String): Boolean {
        return encoder.encode(rand, secret) == sig
    }
}

@Service
class DefaultSignatureEncoder: WorkerSignatureEncoder {

    fun hash(s: String): String {
        return MessageDigest.getInstance("SHA-512")
                .digest(s.toByteArray())
                .toHexString()
    }

    override fun encode(rand: String, secret: String): String {
        return hash(rand + "#" + secret)
    }
}