package dev.paulshields.aoc.day8

data class LineOfCode(var operation: CodeOperation, val argument: Int) {
    companion object {
        private val lineOfCodeRegex = Regex("([a-z]+) ([+-]\\d+)")

        fun parseAll(linesOfCode: String) = lineOfCodeRegex
            .findAll(linesOfCode)
            .map {
                LineOfCode(CodeOperation.fromString(it.groupValues[1]), it.groupValues[2].toInt())
            }
            .toList()
    }
}