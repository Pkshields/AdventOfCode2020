package dev.paulshields.aoc.common

data class Point(val x: Int, val y: Int) {
    operator fun plus(point: Point) = Point(x + point.x, y + point.y)
    operator fun minus(point: Point) = Point(x - point.x, y - point.y)
    operator fun times(factor: Int) = Point(x * factor, y * factor)
}
