package dev.paulshields.aoc.day2

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class PasswordPhilosophyTest {
    @Nested
    inner class ParsePasswordEntryTests {
        @TestFactory
        fun `should be able to parse password entry`() = parameterizedTest(
            "1-3 a: abcde" to PasswordEntry('a', 1, 3, "abcde"),
            "10-12 x: cdefg" to PasswordEntry('x', 10, 12, "cdefg")
        ).runTest { (input, expectedOutput) ->
            val output = parsePasswordEntry(input)

            assertThat(output).isEqualTo(expectedOutput)
        }

        @Test
        fun `should handle invalid inputs`() {
            val invalidInput = "Can't parse this!"

            val output = parsePasswordEntry(invalidInput)

            assertThat(output).isNull()
        }
    }

    @TestFactory
    fun `should correctly validate password using the char count rule`() = parameterizedTest(
        PasswordEntry('a', 1, 3, "abcde") to true,
        PasswordEntry('a', 1, 3, "aaade") to true,
        PasswordEntry('b', 10, 12, "abcdefghij") to false,
        PasswordEntry('b', 1, 2, "bbbbb") to false
    ).runTest { (input, expectedCount) ->
        val count = passwordIsValidByTheCharCountRule(input)

        assertThat(count).isEqualTo(expectedCount)
    }

    @TestFactory
    fun `should correctly validate password using the char position rule`() = parameterizedTest(
        PasswordEntry('a', 1, 3, "abcde") to true,
        PasswordEntry('x', 3, 5, "abcdx") to true,
        PasswordEntry('y', 10, 12, "abcdefghiyyy") to false,
        PasswordEntry('z', 3, 5, "abcde") to false,
    ).runTest { (input, expectedCount) ->
        val count = passwordIsValidByTheCharPositionRule(input)

        assertThat(count).isEqualTo(expectedCount)
    }
}