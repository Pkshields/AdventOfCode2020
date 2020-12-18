package dev.paulshields.aoc.day10

import dev.paulshields.aoc.common.readFileAsStringList

fun main() {
    println(" ** Day 10: Adapter Array ** \n")

    val joltageAdapters = readFileAsStringList("/day10/JoltageAdapters.txt")
        .mapNotNull { it.toIntOrNull() }
        .let { compileListOfJoltageAdaptors(it) }

    val differencesInJoltage = countNumberOfDifferencesBetweenJoltageInAdapterChain(joltageAdapters)
    val countOf1JoltDifferences = differencesInJoltage[1] ?: 0
    val countOf3JoltDifferences = differencesInJoltage[3] ?: 0

    val part1Answer = countOf1JoltDifferences * countOf3JoltDifferences
    println("$countOf1JoltDifferences have a 1 jolt difference between adapters, " +
            "$countOf3JoltDifferences have a 3 jolt difference between adapters, " +
            "thus the answer is ${part1Answer}!")

    val differentAdapterArrangementsCount = countNumberOfPotentialAdapterChains(joltageAdapters.sorted())
    println("The number of different valid adapter arrangements is $differentAdapterArrangementsCount!")
}

fun compileListOfJoltageAdaptors(adaptersInBag: List<Int>) = adaptersInBag
    .toMutableList()
    .also {
        it.add(0, 0)
        it.add(max(it) + 3)
    }
    .toList()

private fun max(list: Iterable<Int>) = list.maxOrNull() ?: 0

fun countNumberOfDifferencesBetweenJoltageInAdapterChain(joltageAdapters: List<Int>): Map<Int, Int> {
    val sortedAdaptors = joltageAdapters.sorted()
    return sortedAdaptors
        .drop(1)
        .mapIndexed { index, item -> item - sortedAdaptors[index] }
        .groupingBy { it }
        .eachCount()
}

fun countNumberOfPotentialAdapterChains(joltageAdapters: List<Int>) =
    countNumberOfPotentialAdapterChainsAtPosition(
        joltageAdapters,
        joltageAdapters.first(),
        mutableMapOf(joltageAdapters.last() to 1))

private fun countNumberOfPotentialAdapterChainsAtPosition(
    joltageAdapters: List<Int>,
    nextElementInChain: Int = 0,
    cache: MutableMap<Int, Long>): Long =
    cache[nextElementInChain] ?: run {
        (nextElementInChain+1 .. nextElementInChain+3)
            .filter { joltageAdapters.contains(it) }
            .map {
                countNumberOfPotentialAdapterChainsAtPosition(joltageAdapters, it, cache)
                    .also { result -> cache[it] = result }
            }
            .sum()
    }