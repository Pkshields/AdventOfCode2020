package dev.paulshields.aoc.day12

import dev.paulshields.aoc.common.Degree
import dev.paulshields.aoc.common.Point

class AssumedEvasiveActionHandler(rawEvasiveInstructions: String) : EvasiveActionHandler(rawEvasiveInstructions) {
    private val directions = listOf(Point(1, 0), Point(0, -1), Point(-1, 0), Point(0, 1))
    private var directionShipIsPointing = directions.first()

    override fun simulateDistanceShipTravels() {
        instructions.forEach { (instruction, parameter) ->
            when(instruction) {
                'N' -> position += Point(0, parameter)
                'S' -> position += Point(0, -parameter)
                'E' -> position += Point(parameter, 0)
                'W' -> position += Point(-parameter, 0)
                'L' -> directionShipIsPointing = rotateShipLeft(parameter)
                'R' -> directionShipIsPointing = rotateShipRight(parameter)
                'F' -> position += directionShipIsPointing * parameter
            }
        }
    }

    private fun rotateShipLeft(degreesToRotate: Int) = rotateShip(degreesToRotate, directions.reversed())
    private fun rotateShipRight(degreesToRotate: Int) = rotateShip(degreesToRotate, directions)

    private fun rotateShip(degreesToRotate: Degree, directions: List<Point>): Point {
        val numberToIterate = degreesToRotate / 90
        return directions.indexOf(directionShipIsPointing).let {
            directions[(it + numberToIterate) % directions.size]
        }
    }
}
