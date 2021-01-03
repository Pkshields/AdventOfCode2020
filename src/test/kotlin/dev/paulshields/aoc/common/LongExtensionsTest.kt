package dev.paulshields.aoc.common

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class LongExtensionsTest {
    @TestFactory
    fun `should calculate to the power of correctly`() = parameterizedTest(
        Pair(2L, 2) to 4L,
        Pair(3L, 3) to 27L,
        Pair(123L, 4) to 228886641L
    ).runTest { (inputs, expectedOutput) ->
        val output = inputs.first.pow(inputs.second)

        assertThat(output).isEqualTo(expectedOutput)
    }

    @TestFactory
    fun `should get the correct bit from a long`() = parameterizedTest(
        Triple(1L, 0, 1),
        Triple(1L, 1, 0),
        Triple(4L, 2, 1),
        Triple(4L, 3, 0),
        Triple(4611686018427387903L, 61, 1),
        Triple(4611686018427387903L, 62, 0)
    ).runTest { (input, bitIndex, expectedOutput) ->
        val output = input.bitAt(bitIndex)

        assertThat(output).isEqualTo(expectedOutput)
    }
}