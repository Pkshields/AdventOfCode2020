package dev.paulshields.aoc.common

typealias Degree = Int
typealias Radian = Double

fun Degree.toRadian(): Radian = this * (Math.PI / 180)
fun Radian.toDegree(): Degree = (this * (180 / Math.PI)).toInt()
