package dev.paulshields.aoc.day6

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class CustomsCustomsTest {
    @TestFactory
    fun `should correctly generate customs form where anyone said yes`() = parameterizedTest(
        "abc" to "abc",
        "ab\nbc" to "abc",
        "abc\nabcd" to "abcd",
        "xy\nyz\na\nb" to "abxyz",
        "" to "",
    ).runTest { (input, expectedOutput) ->
        val result = combineAnswersWhereAnyoneSaidYesIntoCustomsForm(input)

        assertThat(result).isEqualTo(expectedOutput)
    }

    @TestFactory
    fun `should correctly generate customs form where everyone said yes`() = parameterizedTest(
        "abc" to "abc",
        "ab\nbc" to "b",
        "abc\nabcd" to "abc",
        "xy\nyz\na\nb" to "",
        "\nabc\nbcd\nbcx\n" to "bc",
        "" to "",
    ).runTest { (input, expectedOutput) ->
        val result = combineAnswersWhereEveryoneSaidYesIntoCustomsForm(input)

        assertThat(result).isEqualTo(expectedOutput)
    }

    @TestFactory
    fun `should correctly count the number of answered questions`() = parameterizedTest(
        listOf("abc", "bcd", "xyz") to 9,
        listOf("a", "b", "") to 2,
        listOf("abcdefgxyz", "ghi", "jkl") to 16,
    ).runTest { (input, expectedOutput) ->
        val result = countAllAnsweredQuestions(input)

        assertThat(result).isEqualTo(expectedOutput)
    }
}