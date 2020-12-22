package dev.paulshields.aoc.day12

import dev.paulshields.aoc.common.Point
import kotlin.math.absoluteValue

abstract class EvasiveActionHandler(rawEvasiveInstructions: String) {
    protected val instructions = rawEvasiveInstructions
        .lines()
        .filter { it.isNotEmpty() }
        .map(Instruction::parseString)

    protected var position = Point(0, 0)

    fun calculateManhattanDistance(): Int {
        simulateDistanceShipTravels()
        return position.run { x.absoluteValue + y.absoluteValue }
    }

    protected abstract fun simulateDistanceShipTravels()
}
