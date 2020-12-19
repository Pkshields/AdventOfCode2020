package dev.paulshields.aoc.day11

import dev.paulshields.aoc.common.plus

class ClosestAdjacentSeatPredictor(input: String) : SeatPredictor(input, 5) {
    override fun calculateSeatsToCheck(x: Int, y: Int) =
        adjacentSeats.mapNotNull { direction -> findClosestSeatInDirection((x to y), direction) }

    private fun findClosestSeatInDirection(seatPosition: Pair<Int, Int>, direction: Pair<Int, Int>): Pair<Int, Int>? {
        var position = seatPosition + direction
        var seat = getSeat(position)
        while (seat?.equals(Seat.FLOOR) == true) {
            position += direction
            seat = getSeat(position)
        }
        return seat?.let { position }
    }
}