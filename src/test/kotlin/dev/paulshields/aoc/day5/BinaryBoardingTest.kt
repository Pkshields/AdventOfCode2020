package dev.paulshields.aoc.day5

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class BinaryBoardingTest {
    @TestFactory
    fun `should correctly calculate the row number from the input string`() = parameterizedTest(
        "FBFBBFFRLR" to 44,
        "FFBBFFFLLL" to 24,
        "BFBFBFBLLR" to 85,
        "BFFBBBBRRR" to 79,
        "FBFBFFFLLL" to 40
    ).runTest { (input, expectedOutput) ->
        val result = calculateRowNumber(input)

        assertThat(result).isEqualTo(expectedOutput)
    }

    @TestFactory
    fun `should correctly calculate the column number from the input string`() = parameterizedTest(
        "FBFBBFFRLR" to 5,
        "FFBBFFFLLL" to 0,
        "BFBFBFBLLR" to 1,
        "BFFBBBBRRR" to 7
    ).runTest { (input, expectedOutput) ->
        val result = calculateColumnNumber(input)

        assertThat(result).isEqualTo(expectedOutput)
    }

    @TestFactory
    fun `should correctly calculate the seat number from the row and column number`() = parameterizedTest(
        Triple(70, 7, 567),
        Triple(14, 7, 119),
        Triple(102, 4, 820),
        Triple(150, 3, 1203),
        Triple(6, 2, 50),
    ).runTest { (columnNumber, rowNumber, expectedOutput) ->
        val result = calculateSeatId(columnNumber, rowNumber)

        assertThat(result).isEqualTo(expectedOutput)
    }

    @TestFactory
    fun `should correctly determine that a seat number is not assigned`() = parameterizedTest(
        listOf(1, 2, 4, 5) to 3,
        listOf(1, 2, 3, 5) to 4,
        listOf(2, 3, 4, 5) to 1,
    ).runTest { (assignedSeats, seatToCheck) ->
        val result = seatNumbersIsNotAssigned(assignedSeats, seatToCheck)

        assertThat(result).isTrue()
    }

    @TestFactory
    fun `should correctly determine that a seat number is assigned`() = parameterizedTest(
        listOf(1, 2, 3, 4, 5) to 3,
        listOf(1, 2, 3, 4, 5) to 4,
        listOf(1, 2, 3, 4, 5) to 1,
    ).runTest { (assignedSeats, seatToCheck) ->
        val result = seatNumbersIsNotAssigned(assignedSeats, seatToCheck)

        assertThat(result).isFalse()
    }

    @TestFactory
    fun `should correctly determine that adjacent seats to a seat number are both assigned`() = parameterizedTest(
        listOf(1, 2, 3, 4, 5) to 3,
        listOf(1, 2, 3, 5) to 4,
        listOf(1, 3, 4, 5) to 2,
    ).runTest { (assignedSeats, seatToCheck) ->
        val result = adjacentSeatNumbersAreAssigned(assignedSeats, seatToCheck)

        assertThat(result).isTrue()
    }

    @TestFactory
    fun `should correctly determine that adjacent seats to a seat number are not both assigned`() = parameterizedTest(
        listOf(1, 3, 4, 5) to 3,
        listOf(1, 2, 3) to 4,
        listOf(2, 4, 5) to 2,
    ).runTest { (assignedSeats, seatToCheck) ->
        val result = adjacentSeatNumbersAreAssigned(assignedSeats, seatToCheck)

        assertThat(result).isFalse()
    }
}