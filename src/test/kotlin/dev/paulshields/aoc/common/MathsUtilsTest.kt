package dev.paulshields.aoc.common

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class MathsUtilsTest {
    @TestFactory
    fun `should round up an integer division in all cases`() = parameterizedTest(
        Pair(10, 5) to 2,
        Pair(10, 3) to 4,
        Pair(100, 40) to 3,
        Pair(100, 4) to 25,
        Pair(100, 6) to 17
    ).runTest { (inputs, expectedOutput) ->
        val output = divideRoundingUp(inputs.first, inputs.second)

        assertThat(output).isEqualTo(expectedOutput)
    }
}