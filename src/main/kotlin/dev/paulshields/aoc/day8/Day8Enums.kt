package dev.paulshields.aoc.day8

enum class CodeOperation {
    ACC, JMP, NOP;

    companion object {
        fun fromString(operation: String) = valueOf(operation.toUpperCase())
    }
}

enum class ExecutionState {
    SHUT_DOWN, RUNNING, BOOT_LOOP, FINISHED
}