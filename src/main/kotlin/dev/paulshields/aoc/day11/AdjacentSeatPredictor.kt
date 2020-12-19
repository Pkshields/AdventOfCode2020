package dev.paulshields.aoc.day11

class AdjacentSeatPredictor(input: String) : SeatPredictor(input, 4) {
    override fun calculateSeatsToCheck(x: Int, y: Int) =
        adjacentSeats.map { (xPos, yPos) -> xPos + x to yPos + y }
}