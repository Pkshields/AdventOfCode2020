package dev.paulshields.aoc.day12

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.TestFactory

class InstructionTest {
    @TestFactory
    fun `should parse instruction correctly`() = parameterizedTest(
        "N1" to Instruction('N', 1),
        "S10" to Instruction('S', 10),
        "E100" to Instruction('E', 100),
        "W99" to Instruction('W', 99),
        "L5" to Instruction('L', 5),
        "R67" to Instruction('R', 67),
        "F101" to Instruction('F', 101)
    ).runTest { (input, expectedOutput) ->
        val result = Instruction.parseString(input)

        assertThat(result).isEqualTo(expectedOutput)
    }
}