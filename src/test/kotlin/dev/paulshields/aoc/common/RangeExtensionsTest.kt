package dev.paulshields.aoc.common

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class RangeExtensionsTest {
    @TestFactory
    fun `should generate a list containing all elements within a range`() = parameterizedTest(
        0..5 to listOf(0, 1, 2, 3, 4, 5),
        3..7 to listOf(3, 4, 5, 6, 7),
        0 until 5 to listOf(0, 1, 2, 3, 4),
        0..10 step 2 to listOf(0, 2, 4, 6, 8, 10)
    ).runTest { (range, expectedOutput) ->
        val result = range.toList()

        assertThat(result).isEqualTo(expectedOutput)
    }
}