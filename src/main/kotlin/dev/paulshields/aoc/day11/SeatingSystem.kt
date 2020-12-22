package dev.paulshields.aoc.day11

import dev.paulshields.aoc.common.readFileAsString

fun main() {
    println(" ** Day 11: Adapter Array ** \n")

    val fileInput = readFileAsString("/day11/SeatingLayout.txt")

    val seatPredictor = AdjacentSeatPredictor(fileInput)
    val finalNumberOfOccupiedSeats = seatPredictor.iterateUntilNoMoreSeatsChange()
    println("The final number of occupied seats is $finalNumberOfOccupiedSeats!")

    val closestAdjacentSeatPredictor = ClosestAdjacentSeatPredictor(fileInput)
    val finalNumberOfOccupiedSeatsPart2 = closestAdjacentSeatPredictor.iterateUntilNoMoreSeatsChange()
    println("The final number of occupied seats for the part 2 algorithm is $finalNumberOfOccupiedSeatsPart2!")
}
