package dev.paulshields.aoc.common

fun String.allIndexesOf(needle: Char): List<Int> {
    val allIndexes = mutableListOf<Int>()

    var indexOf = this.indexOf(needle)
    while (indexOf != -1) {
        allIndexes.add(indexOf)
        indexOf = this.indexOf(needle, indexOf + 1)
    }

    return allIndexes.toList()
}
