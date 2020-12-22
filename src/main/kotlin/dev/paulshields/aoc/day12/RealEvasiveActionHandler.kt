package dev.paulshields.aoc.day12

import dev.paulshields.aoc.common.Degree
import dev.paulshields.aoc.common.Point
import dev.paulshields.aoc.common.toRadian
import kotlin.math.cos
import kotlin.math.sin

class RealEvasiveActionHandler(rawEvasiveInstructions: String) : EvasiveActionHandler(rawEvasiveInstructions) {
    var waypointPosition = Point(10, 1)

    override fun simulateDistanceShipTravels() {
        instructions.forEach { (instruction, parameter) ->
            when(instruction) {
                'N' -> waypointPosition += Point(0, parameter)
                'S' -> waypointPosition += Point(0, -parameter)
                'E' -> waypointPosition += Point(parameter, 0)
                'W' -> waypointPosition += Point(-parameter, 0)
                'L' -> waypointPosition = rotateWaypointAnticlockwise(parameter)
                'R' -> waypointPosition = rotateWaypointClockwise(parameter)
                'F' -> position += waypointPosition * parameter
            }
        }
    }

    private fun rotateWaypointClockwise(angleInDegrees: Degree) = rotateWaypoint(angleInDegrees * -1)
    private fun rotateWaypointAnticlockwise(angleInDegrees: Degree) = rotateWaypoint(angleInDegrees)

    private fun rotateWaypoint(angleInDegrees: Degree): Point {
        val sin = sin(angleInDegrees.toRadian()).toInt()
        val cos = cos(angleInDegrees.toRadian()).toInt()
        return waypointPosition.run { Point(x * cos - y * sin, x * sin + y * cos) }
    }
}
