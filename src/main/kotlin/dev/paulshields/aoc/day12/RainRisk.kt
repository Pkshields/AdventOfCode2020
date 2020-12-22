package dev.paulshields.aoc.day12

import dev.paulshields.aoc.common.readFileAsString

fun main() {
    println(" ** Day 12: Rain Risk ** \n")

    val fileInput = readFileAsString("/day12/EvasiveActionDirections.txt")

    val assumedEvasiveActionHandler = AssumedEvasiveActionHandler(fileInput)
    val assumedManhattanDistance = assumedEvasiveActionHandler.calculateManhattanDistance()

    println("The manhattan distance travelled is $assumedManhattanDistance!")

    val realEvasiveActionHandler = RealEvasiveActionHandler(fileInput)
    val realManhattanDistance = realEvasiveActionHandler.calculateManhattanDistance()

    println("The real manhattan distance travelled is $realManhattanDistance!")
}
