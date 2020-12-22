package dev.paulshields.aoc.common

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class PointTest {
    @TestFactory
    fun `should correctly add points together`() = parameterizedTest(
        Triple(Point(1, 1), Point(1, 1), Point(2, 2)),
        Triple(Point(10, -10), Point(5, 5), Point(15, -5)),
        Triple(Point(123, 456), Point(789, 123), Point(912, 579))
    ).runTest { (target, additor, expectedResult) ->
        val result = target + additor

        assertThat(result).isEqualTo(expectedResult)
    }

    @TestFactory
    fun `should correctly subtract points together`() = parameterizedTest(
        Triple(Point(1, 1), Point(1, 1), Point(0, 0)),
        Triple(Point(10, -10), Point(5, 5), Point(5, -15)),
        Triple(Point(123, 456), Point(789, 123), Point(-666, 333))
    ).runTest { (target, subtrachend, expectedResult) ->
        val result = target - subtrachend

        assertThat(result).isEqualTo(expectedResult)
    }

    @TestFactory
    fun `should correctly multiply point and a factor together`() = parameterizedTest(
        Triple(Point(1, 1), 5, Point(5, 5)),
        Triple(Point(10, -10), 13, Point(130, -130)),
        Triple(Point(123, 456), 789, Point(97047, 359784))
    ).runTest { (target, factor, expectedResult) ->
        val result = target * factor

        assertThat(result).isEqualTo(expectedResult)
    }
}