package dev.paulshields.aoc.day11

enum class Seat {
    OCCUPIED, EMPTY, FLOOR;

    companion object {
        fun parseChar(char: Char) = when(char) {
            'L' -> EMPTY
            '#' -> OCCUPIED
            else -> FLOOR
        }
    }
}