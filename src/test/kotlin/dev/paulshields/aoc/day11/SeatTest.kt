package dev.paulshields.aoc.day11

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class SeatTest {
    @TestFactory
    fun `should parse char correctly`() = parameterizedTest(
        '.' to Seat.FLOOR,
        'L' to Seat.EMPTY,
        '#' to Seat.OCCUPIED,
        ' ' to Seat.FLOOR
    ).runTest { (input, expectedOutput) ->
        val result = Seat.parseChar(input)

        assertThat(result).isEqualTo(expectedOutput)
    }
}