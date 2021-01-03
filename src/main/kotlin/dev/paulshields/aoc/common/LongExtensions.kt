package dev.paulshields.aoc.common

import kotlin.math.pow

fun Long.pow(power: Int) = this.toDouble().pow(power).toLong()

fun Long.bitAt(index: Int) = ((this shr index) and 1).toInt()
