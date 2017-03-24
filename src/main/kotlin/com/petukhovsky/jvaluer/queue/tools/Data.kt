package com.petukhovsky.jvaluer.queue.tools

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.nio.charset.Charset
import java.util.*

/**
 * Created by Arthur Petukhovsky on 3/24/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Data(
    val type: DataType,
    val base64: String? = null,
    val string: String? = null
) {

    fun toByteArray(): ByteArray {
        return when(type) {
            DataType.base64 -> decoder.decode(base64!!)
            DataType.string -> string!!.toByteArray()
        }
    }

    override fun toString(): String {
        return when(type) {
            DataType.base64 -> decoder.decode(base64!!).toString(Charset.defaultCharset())
            DataType.string -> string!!
        }
    }

    companion object {

        val encoder = Base64.getEncoder()
        val decoder = Base64.getDecoder()

        fun base64(data: ByteArray): Data {
            return Data(
                    DataType.base64,
                    base64 = encoder.encodeToString(data)
            )
        }

        fun base64(data: String): Data {
            return base64(data.toByteArray())
        }

        fun string(string: String): Data {
            return Data(
                    DataType.string,
                    string = string
            )
        }
    }
}

enum class DataType {
    base64, string
}