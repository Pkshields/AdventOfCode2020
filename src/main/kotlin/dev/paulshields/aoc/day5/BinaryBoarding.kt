package dev.paulshields.aoc.day5

import dev.paulshields.aoc.common.readFileAsStringList

private const val lowerRowRegionId = 'F'
private const val lowerColumnRegionId = 'L'
private val rows = 0..127
private val columns = 0..7

fun main() {
    println(" ** Day 5: Binary Boarding ** \n")

    val boardingPasses = readFileAsStringList("/day5/BoardingPasses.txt")

    val allAssignedSeatNumbers = boardingPasses.map {
        val rowNumber = calculateRowNumber(it)
        val seatNumber = calculateColumnNumber(it)
        calculateSeatId(rowNumber, seatNumber)
    }

    val largestSeatNumber = allAssignedSeatNumbers.maxOrNull() ?: 0

    println("There largest seat number is $largestSeatNumber!")

    val mySeatNumber = (0..largestSeatNumber)
        .toList()
        .first {
            seatNumbersIsNotAssigned(allAssignedSeatNumbers, it)
                    && adjacentSeatNumbersAreAssigned(allAssignedSeatNumbers, it)
        }

    println("My seat number is $mySeatNumber!")
}

fun calculateRowNumber(seatSpecification: String) =
    seatSpecification
        .take(7)
        .fold(rows) { range, regionId ->
            binarySplitRangeByRegionId(range, regionId, lowerRowRegionId)
        }
        .first

fun calculateColumnNumber(seatSpecification: String) =
    seatSpecification
        .drop(7)
        .fold(columns) { range, regionId ->
            binarySplitRangeByRegionId(range, regionId, lowerColumnRegionId)
        }
        .first

private fun binarySplitRangeByRegionId(range: IntRange, regionId: Char, lowerRegionId: Char): IntRange {
    val rangeLength = range.last - range.first
    val binarySplitPosition = range.first + (rangeLength / 2)

    return if (regionId == lowerRegionId) {
        range.first .. binarySplitPosition
    } else {
        binarySplitPosition + 1 .. range.last
    }
}

fun calculateSeatId(rowNumber: Int, columnNumber: Int) = (rowNumber * 8) + columnNumber

fun seatNumbersIsNotAssigned(allAssignedSeatNumbers: List<Int>, seatNumber: Int) =
    !allAssignedSeatNumbers.contains(seatNumber)

fun adjacentSeatNumbersAreAssigned(allAssignedSeatNumbers: List<Int>, seatNumber: Int) =
    allAssignedSeatNumbers.contains(seatNumber - 1) && allAssignedSeatNumbers.contains(seatNumber + 1)
