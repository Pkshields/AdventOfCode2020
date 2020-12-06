package dev.paulshields.aoc.day1

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class ReportRepairTest {
    @TestFactory
    fun `should calculate the correct sum entries`() = parameterizedTest(
        Triple(listOf(1, 2, 3), 3, Pair(1, 2)),
        Triple(listOf(10, 50, 60), 110, Pair(50, 60)),
        Triple(listOf(15, 75, 250), 265, Pair(15, 250))
    ).runTest { (possibleSumEntries, additionTarget, expectedResult) ->
        val result = findSum(possibleSumEntries, additionTarget)

        assertThat(result).isEqualTo(expectedResult)
    }
}