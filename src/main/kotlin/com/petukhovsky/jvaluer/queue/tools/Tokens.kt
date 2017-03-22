package com.petukhovsky.jvaluer.queue.tools

import java.util.*

/**
 * Created by Arthur Petukhovsky on 3/22/2017.
 */

fun generateToken(
        length: Int = 25,
        dict: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9'),
        random: Random = Random()
): String {
    val sb = StringBuilder()
    for (i in 1..length) sb.append(dict.random(random))
    return sb.toString()
}