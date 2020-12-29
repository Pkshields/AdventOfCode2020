package dev.paulshields.aoc.day13

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class ShuttleSearchTest {
    @Test
    fun `should correctly calculate the earliest airport bus to leave (times the minutes you need to wait)`() {
        val thing = listOf("7", "13", "59", "31", "19")

        val result = calculateTheEarliestAirportBus(939, thing)

        assertThat(result).isEqualTo(BusWaitTime(59, 5))
    }

    @Test
    fun `should correctly handle any non-numeric values`() {
        val thing = listOf("7", "13", "x", "x", "59", "x", "31", "19")

        val result = calculateTheEarliestAirportBus(939, thing)

        assertThat(result).isEqualTo(BusWaitTime(59, 5))
    }

    @TestFactory
    fun `should calculate the earliest timestamp such that all of the listed bus IDs depart at offsets matching their positions in the list`() =
        parameterizedTest(
            listOf("17", "x", "13", "19") to 3417L,
            listOf("67", "7", "59", "61") to 754018L,
            listOf("67", "x", "7", "59", "61") to 779210L,
            listOf("67", "7", "x", "59", "61") to 1261476L,
            listOf("1789", "37", "47", "1889") to 1202161486L
        ).runTest { (input, expectedOutput) ->
            val result = findEarliestTimestampThatMatchesPattern(input)

            assertThat(result).isEqualTo(expectedOutput)
        }
}