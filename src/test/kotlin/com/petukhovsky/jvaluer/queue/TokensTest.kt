package com.petukhovsky.jvaluer.queue

import com.petukhovsky.jvaluer.queue.tools.generateToken
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Created by Arthur Petukhovsky on 3/22/2017.
 */
class TokensTest {
    @Test
    fun test() {
        val len = 33
        assertEquals(generateToken(len).length, len)
    }
}