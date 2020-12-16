package dev.paulshields.aoc.day9

import dev.paulshields.aoc.common.firstIndexed
import dev.paulshields.aoc.common.readFileAsStringList

fun main() {
    println(" ** Day 9: Encoding Error ** \n")

    val xmasData = readFileAsStringList("/day9/XmasData.txt")
        .map { it.toLong() }

    val notXmasEncodedNumber = findNumberThatIsNotXmasEncoded(xmasData)

    println("The first value that does not match the rules of the XMAS encoding is $notXmasEncodedNumber!")

    val addends = findContiguousSetThatSumsTo(notXmasEncodedNumber, xmasData)
    val result = addends
        .sorted()
        .let { it.first() + it.last() }

    println("The answer to part 2 is $result!")
}

fun findNumberThatIsNotXmasEncoded(xmasData: List<Long>, preambleLength: Int = 25) = xmasData
    .drop(preambleLength)
    .firstIndexed { index, finalSum ->
        val possibleAddends = xmasData.subList(index, index + preambleLength)
        val validPairing = findSum(finalSum, possibleAddends)
        validPairing == null
    }

private fun findSum(finalSum: Long, possibleAddends: List<Long>) = possibleAddends
    .mapNotNull { one ->
        val two = possibleAddends.firstOrNull { two ->
            (one + two) == finalSum
        }

        two?.let { Pair(one, two) }
    }.firstOrNull()

fun findContiguousSetThatSumsTo(result: Long, possibleAddends: List<Long>) = possibleAddends
    .indices
    .mapNotNull { start ->
        possibleAddends
            .indices
            .drop(start)
            .map { end -> possibleAddends.subList(start, end + 1) }
            .firstOrNull { possibleResult -> possibleResult.sum() == result }
    }
    .first()