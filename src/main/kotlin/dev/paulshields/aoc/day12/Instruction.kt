package dev.paulshields.aoc.day12

data class Instruction(val action: Char, val parameter: Int) {
    companion object {
        fun parseString(input: String) = Instruction(
            input.first(),
            input.substring(1).toInt()
        )
    }
}
