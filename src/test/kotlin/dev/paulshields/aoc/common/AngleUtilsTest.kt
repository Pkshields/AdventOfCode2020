package dev.paulshields.aoc.common

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestFactory

class AngleUtilsTest {
    @Nested
    inner class DegreeTests {
        @TestFactory
        fun `should parse instruction correctly`() = parameterizedTest(
            90 to 1.5707963267948966,
            180 to 3.141592653589793,
            270 to 4.71238898038469,
        ).runTest { (input: Degree, expectedOutput: Radian) ->
            val result = input.toRadian()

            assertThat(result).isEqualTo(expectedOutput)
        }
    }

    @Nested
    inner class RadianTests {
        @TestFactory
        fun `should parse instruction correctly`() = parameterizedTest(
            1.5707963267948966 to 90,
            3.141592653589793 to 180,
            4.71238898038469 to 270,
        ).runTest { (input: Radian, expectedOutput: Degree) ->
            val result = input.toDegree()

            assertThat(result).isEqualTo(expectedOutput)
        }
    }
}