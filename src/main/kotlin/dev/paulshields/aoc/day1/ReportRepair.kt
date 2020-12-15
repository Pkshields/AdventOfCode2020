package dev.paulshields.aoc.day1

import dev.paulshields.aoc.common.readFileAsString

fun main() {
    println(" ** Day 1: Report Repair ** \n")

    val expenseReport = readFileAsString("/day1/ExpenseReport.txt")
        .lines()
        .mapNotNull { it.toIntOrNull() }

    val validPair = findSum(expenseReport, 2020)
    println("Found values ${validPair.first} and ${validPair.second} equal 2020!")

    val solution = validPair.first * validPair.second
    println("The first solution then, by multiplying these together, is $solution.")

    val validTriple = findSumOfThree(expenseReport, 2020)
    println("Also found values ${validTriple.first}, ${validTriple.second} and ${validTriple.third} equal 2020!")

    val solutionTwo = validTriple.first * validTriple.second * validTriple.third
    println("The second solution then, by multiplying these together, is $solutionTwo.")
}

fun findSum(possibleValues: List<Int>, result: Int): Pair<Int, Int> {
    val sortedPossibleValues = possibleValues.sorted()

    return sortedPossibleValues
        .mapIndexedNotNull { index, possibleValueOne ->
            sortedPossibleValues
                .drop(index)
                .dropWhile { possibleValueOne + it != result }
                .take(1)
                .map { Pair(possibleValueOne, it) }
                .firstOrNull()
        }.first()
}

fun findSumOfThree(possibleValues: List<Int>, result: Int): Triple<Int, Int, Int> {
    val sortedPossibleValues = possibleValues.sorted()

    return sortedPossibleValues
        .mapIndexedNotNull { firstIndex, possibleValueOne ->
            sortedPossibleValues
                .drop(firstIndex)
                .mapIndexedNotNull { secondIndex, possibleValueTwo ->
                    sortedPossibleValues
                        .drop(secondIndex)
                        .dropWhile { possibleValueOne + possibleValueTwo + it != result }
                        .take(1)
                        .map { Triple(possibleValueOne, possibleValueTwo, it) }
                        .firstOrNull()
                }.firstOrNull()
        }.first()
}