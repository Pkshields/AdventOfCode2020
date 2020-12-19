package dev.paulshields.aoc.common

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestFactory

class PairExtensionsTest {
    @Nested
    inner class PairOfIntsAddition {
        @TestFactory
        fun `should correctly add pairs of integers`() = parameterizedTest(
            Triple(1 to 1, 2 to 2, 3 to 3),
            Triple(4 to 5, 2 to 2, 6 to 7),
            Triple(150 to 1, -145 to 4, 5 to 5),
            Triple(-1 to -1, -2 to -2, -3 to -3)
        ).runTest { (target, second, expectedResult) ->
            val result = target + second

            assertThat(result).isEqualTo(expectedResult)
        }
    }
}