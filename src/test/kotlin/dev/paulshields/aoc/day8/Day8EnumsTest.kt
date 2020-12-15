package dev.paulshields.aoc.day8

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class Day8EnumsTest {
    @TestFactory
    fun `should correctly parse enum code operation value from string`() = parameterizedTest(
        "acc" to CodeOperation.ACC,
        "jmp" to CodeOperation.JMP,
        "nop" to CodeOperation.NOP
    ).runTest { (input, expectedOutput) ->
        val result = CodeOperation.fromString(input)

        assertThat(result).isEqualTo(expectedOutput)
    }
}