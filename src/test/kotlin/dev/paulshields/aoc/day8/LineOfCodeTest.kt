package dev.paulshields.aoc.day8

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class LineOfCodeTest {
    private val input = "nop +0\n" +
            "acc +1\n" +
            "jmp -4"

    private val expectedOutput = listOf(
        LineOfCode(CodeOperation.NOP, 0),
        LineOfCode(CodeOperation.ACC, 1),
        LineOfCode(CodeOperation.JMP, -4))

    @Test
    fun `should parse all lines of code from string`() {
        val result = LineOfCode.parseAll(input)

        assertThat(result).isEqualTo(expectedOutput)
    }

    @Test
    fun `should handle empty lines when parsing code`() {
        val inputWithNewLine = input + "\n\n\n"

        val result = LineOfCode.parseAll(inputWithNewLine)

        assertThat(result).isEqualTo(expectedOutput)
    }

    @Test
    fun `should handle empty strings when parsing code`() {
        val result = LineOfCode.parseAll("")

        assertThat(result).isEqualTo(emptyList())
    }
}