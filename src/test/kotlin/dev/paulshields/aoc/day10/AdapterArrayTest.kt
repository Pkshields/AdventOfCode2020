package dev.paulshields.aoc.day10

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.containsAll
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class AdapterArrayTest {
    @Test
    fun `should compile correct list of adapters`() {
        val input = arrayOf(6, 7, 8, 10)

        val result = compileListOfJoltageAdaptors(input.toList())

        assertThat(result).containsAll(*input)
        assertThat(result).contains(0)
        assertThat(result).contains(13)
        assertThat(result.size).isEqualTo(6)
    }

    @TestFactory
    fun `should count number of differences between joltage in adapter chain`() = parameterizedTest(
        listOf(1, 2, 3, 4, 5) to mapOf(1 to 4),
        listOf(1, 4, 7, 10, 13) to mapOf(3 to 4),
        listOf(1, 2, 3, 6, 9, 12, 13, 14, 15, 18, 19, 20) to mapOf(1 to 7, 3 to 4)
    ).runTest { (input, expectedOutput) ->
        val result = countNumberOfDifferencesBetweenJoltageInAdapterChain(input)

        assertThat(result).isEqualTo(expectedOutput)
    }

    @TestFactory
    fun `should count number of potential adapter chains`() = parameterizedTest(
        listOf(1, 2, 3, 4, 5) to 7L,
        listOf(1, 4, 7, 10, 13) to 1L,
        listOf(1, 2, 3, 6, 9, 12, 13, 14, 15, 18, 19, 20) to 16L
    ).runTest { (input, expectedOutput) ->
        val result = countNumberOfPotentialAdapterChains(input)

        assertThat(result).isEqualTo(expectedOutput)
    }
}