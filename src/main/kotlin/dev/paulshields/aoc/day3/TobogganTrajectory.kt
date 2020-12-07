package dev.paulshields.aoc.day3

import dev.paulshields.aoc.common.divideRoundingUp
import dev.paulshields.aoc.common.readFileAsStringList

fun main() {
    println(" ** Day 3: Toboggan Trajectory ** \n")

    val map = readFileAsStringList("/day3/Map.txt")

    val result = generateSlopePath(1, 3, map)
        .calculateNumberOfTreesHit(map)

    println("The toboggan will hit $result trees!")

    val treesHitInOneOne = generateSlopePath(1, 1, map).calculateNumberOfTreesHit(map)
    val treesHitInOneFive = generateSlopePath(1, 5, map).calculateNumberOfTreesHit(map)
    val treesHitInOneSeven = generateSlopePath(1, 7, map).calculateNumberOfTreesHit(map)
    val treesHitInTwoOne = generateSlopePath(2, 1, map).calculateNumberOfTreesHit(map)

    val part2Result = treesHitInOneOne.toLong() * result * treesHitInOneFive * treesHitInOneSeven * treesHitInTwoOne

    println("The answer to part 2 is $part2Result!")
}

fun generateSlopePath(down: Int, right: Int, map: List<String>): List<SlopeStep> {
    if (map.isEmpty()) return emptyList()

    val mapLineLength = map[0].length
    val numberStepsDown = divideRoundingUp(map.size, down)

    return (0 until numberStepsDown)
        .map {
            SlopeStep((right * it) % mapLineLength, down * it)
        }
}

fun List<SlopeStep>.calculateNumberOfTreesHit(map: List<String>) = this
    .map { map[it.y][it.x] == '#' }
    .filter { it }
    .count()

data class SlopeStep(val x: Int, val y: Int)