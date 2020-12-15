package dev.paulshields.aoc.day8

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class HandheldDeviceTest {
    @Test
    fun `should have correct initial state`() {
        val target = createTargetWithCode()

        assertTargetState(target, ExecutionState.SHUT_DOWN, 0)
    }

    @Test
    fun `should handle boot code with just no operation inputs`() {
        val target = createTargetWithCode(
            LineOfCode(CodeOperation.NOP, 1),
            LineOfCode(CodeOperation.NOP, 2),
            LineOfCode(CodeOperation.NOP, 3))

        target.boot()

        assertTargetState(target, ExecutionState.FINISHED, 0)
    }

    @Test
    fun `should handle boot code with just positive accumulators`() {
        val target = createTargetWithCode(
            LineOfCode(CodeOperation.ACC, 1),
            LineOfCode(CodeOperation.ACC, 2),
            LineOfCode(CodeOperation.ACC, 3))

        target.boot()

        assertTargetState(target, ExecutionState.FINISHED, 6)
    }

    @Test
    fun `should handle boot code with positive and negative accumulators`() {
        val target = createTargetWithCode(
            LineOfCode(CodeOperation.ACC, -1),
            LineOfCode(CodeOperation.ACC, 2),
            LineOfCode(CodeOperation.ACC, 3))

        target.boot()

        assertTargetState(target, ExecutionState.FINISHED, 4)
    }

    @Test
    fun `should handle boot code with jump operations`() {
        val target = createTargetWithCode(
            LineOfCode(CodeOperation.ACC, 1),
            LineOfCode(CodeOperation.JMP, 2),
            LineOfCode(CodeOperation.ACC, 3),
            LineOfCode(CodeOperation.ACC, 4),
            LineOfCode(CodeOperation.ACC, 5))

        target.boot()

        assertTargetState(target, ExecutionState.FINISHED, 10)
    }

    @Test
    fun `should detect boot loop`() {
        val target = createTargetWithCode(
            LineOfCode(CodeOperation.ACC, 1),
            LineOfCode(CodeOperation.ACC, 2),
            LineOfCode(CodeOperation.JMP, -2))

        target.boot()

        assertThat(target.currentState).isEqualTo(ExecutionState.BOOT_LOOP)
    }

    @Test
    fun `should handle empty list for boot loop code`() {
        val target = createTargetWithCode()

        target.boot()

        assertTargetState(target, ExecutionState.FINISHED, 0)
    }

    private fun createTargetWithCode(vararg bootCode: LineOfCode) = HandheldDevice(bootCode.toList())

    private fun assertTargetState(target: HandheldDevice, executionState: ExecutionState, accumulator: Int) {
        assertThat(target.currentState).isEqualTo(executionState)
        assertThat(target.accumulator).isEqualTo(accumulator)
    }
}