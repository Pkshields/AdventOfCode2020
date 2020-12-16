package dev.paulshields.aoc.common

fun <T> Iterable<T>.firstIndexedOrNull(predicate: (Int, T) -> Boolean): T? {
    val indexedData = this.mapIndexed { one, two -> Pair(one, two) }

    for (pair in indexedData) {
        val result = predicate(pair.first, pair.second)
        if (result) return pair.second
    }

    return null
}

fun <T> Iterable<T>.firstIndexed(predicate: (Int, T) -> Boolean) =
    firstIndexedOrNull(predicate)
        ?: throw NoSuchElementException("Collection contains no element matching the predicate.")