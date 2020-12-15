package dev.paulshields.aoc.day8

open class HandheldDevice(protected val bootCode: List<LineOfCode>) {
    var currentState = ExecutionState.SHUT_DOWN
        private set
    var accumulator = 0
        private set

    protected val executedLinesOfCode = mutableListOf<Int>()
    private var currentLineOfCode = 0

    open fun boot() {
        initializeAndStart()

        while (currentState == ExecutionState.RUNNING) {
            val lineOfCode = bootCode.getOrNull(currentLineOfCode)
            when(lineOfCode?.operation) {
                CodeOperation.ACC -> accumulator += lineOfCode.argument
                CodeOperation.JMP -> currentLineOfCode += lineOfCode.argument - 1
            }

            checkNextLineOfCode()

            if (currentState != ExecutionState.RUNNING) break
        }
    }

    private fun initializeAndStart() {
        currentState = ExecutionState.RUNNING
        accumulator = 0
        currentLineOfCode = 0
        executedLinesOfCode.clear()
    }

    private fun checkNextLineOfCode() {
        if (executedLinesOfCode.contains(++currentLineOfCode)) {
            currentState = ExecutionState.BOOT_LOOP
        } else if (currentLineOfCode >= bootCode.size) {
            currentState = ExecutionState.FINISHED
        } else {
            executedLinesOfCode.add(currentLineOfCode)
        }
    }
}