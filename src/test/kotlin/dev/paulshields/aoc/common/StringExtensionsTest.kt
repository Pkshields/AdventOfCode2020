package dev.paulshields.aoc.common

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class StringExtensionsTest {
    @TestFactory
    fun `should get the correct bit from a long`() = parameterizedTest(
        Triple("XXXXXX", 'X', listOf(0, 1, 2, 3, 4, 5)),
        Triple("XXXXXX", 'Y', emptyList()),
        Triple("ABCDEF", 'A', listOf(0)),
        Triple("ABCDEF", 'B', listOf(1)),
        Triple("ABCDEF", 'C', listOf(2)),
        Triple("ABCAAB", 'A', listOf(0, 3, 4)),
        Triple("ABCAAB", 'B', listOf(1, 5)),
        Triple("ABCAAB", 'C', listOf(2)),
        Triple("ABCAAB", 'a', emptyList()),
    ).runTest { (input, needle, expectedOutput) ->
        val output = input.allIndexesOf(needle)

        assertThat(output).isEqualTo(expectedOutput)
    }
}