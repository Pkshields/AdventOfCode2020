package dev.paulshields.aoc.day8

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class RepairableHandheldDeviceTest {
    @Test
    fun `should handle boot code that needs a jump line repaired`() {
        val target = createTargetWithCode(
            LineOfCode(CodeOperation.ACC, 5),
            LineOfCode(CodeOperation.JMP, 1),
            LineOfCode(CodeOperation.JMP, -1),
            LineOfCode(CodeOperation.NOP, 3))

        target.boot()

        assertThat(target.currentState).isEqualTo(ExecutionState.FINISHED)
        assertThat(target.accumulator).isEqualTo(5)
    }

    /**
     * Note to self. I can't think of a reason a NOP would need repaired instead of a JMP line (when stepping through
     * the boot looped code in reverse like I am doing here). Any situation that would fix the code by flipping a NOP
     * line would also work by flipping a JMP line, right? Flipping a NOP could lead to a different accumulator being
     * calculated, but this working in reverse logic works for my input, so :shrug:
     **/

    private fun createTargetWithCode(vararg bootCode: LineOfCode) = RepairableHandheldDevice(bootCode.toList())
}