package dev.paulshields.aoc.common

import kotlin.math.ceil

fun divideRoundingUp(number: Int, divisor: Int) = ceil(number.toDouble() / divisor).toInt()
