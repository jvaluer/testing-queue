package com.petukhovsky.jvaluer.queue.tools

import java.util.*

/**
 * Created by Arthur Petukhovsky on 3/22/2017.
 */

fun IntRange.random(random: Random = Random()): Int {
    return this.first + random.nextInt(this.last - this.first + 1)
}

fun<T> List<T>.random(random: Random = Random()): T {
    return this[this.indices.random(random)]
}