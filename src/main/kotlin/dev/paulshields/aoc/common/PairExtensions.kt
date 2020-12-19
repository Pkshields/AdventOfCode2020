package dev.paulshields.aoc.common

operator fun Pair<Int, Int>.plus(point: Pair<Int, Int>) = Pair(first + point.first, second + point.second)
