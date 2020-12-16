package dev.paulshields.aoc.day9

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class EncodingErrorTest {
    @TestFactory
    fun `should find the number that is not xmas encoded`() = parameterizedTest(
        listOf<Long>(1, 2, 3, 4, 5, 6, 8, 5) to 5L,
        listOf<Long>(1, 2, 3, 4, 5, 6, -1, 11) to -1L,
        listOf<Long>(1, 2, 3, 4, 5, 6, 8, 11, 19, 11, 50, 30) to 50L
    ).runTest { (data, expectedResult) ->
        val result = findNumberThatIsNotXmasEncoded(data, 5)

        assertThat(result).isEqualTo(expectedResult)
    }

    @TestFactory
    fun `should find contiguous set that sums to`() = parameterizedTest(
        Triple(listOf<Long>(1, 2, 3, 4, 5, 6, 8, 5), 15L, listOf<Long>(1, 2, 3, 4, 5)),
        Triple(listOf<Long>(1, 2, 3, 4, 11, 6, 8, 5), 19L, listOf<Long>(6, 8, 5)),
        Triple(listOf<Long>(18, 25, 46, 33, 22, 11), 33L, listOf<Long>(33)),
        Triple(listOf<Long>(-1, -2, -5, -9, 44), -16L, listOf<Long>(-2, -5, -9)),
    ).runTest { (data, sumResult, expectedOutput) ->
        val result = findContiguousSetThatSumsTo(sumResult, data)

        assertThat(result).isEqualTo(expectedOutput)
    }
}
