package dev.paulshields.aoc.day13

import dev.paulshields.aoc.common.readFileAsStringList

fun main() {
    println(" ** Day 13: Shuttle Search ** \n")

    val fileInput = readFileAsStringList("/day13/BusTimes.txt")
    val leavingAirportTime = fileInput[0].toInt()
    val busIds = fileInput[1]
        .split(",")

    val busToCatch = calculateTheEarliestAirportBus(leavingAirportTime, busIds)
    val part1Result = busToCatch.busID * busToCatch.waitTime

    println("The bus we need to get is ${busToCatch.busID}, leaving in ${busToCatch.waitTime} minutes. Result: $part1Result!")

    val day2Result = findEarliestTimestampThatMatchesPattern(busIds)

    println("The answer to the shuttle companies contest is $day2Result!")
}

fun calculateTheEarliestAirportBus(leavingAirportTime: Int, busIds: List<String>) = busIds
    .mapNotNull { it.toIntOrNull() }
    .map { BusWaitTime(it, it - (leavingAirportTime % it)) }
    .sortedBy { it.waitTime }
    .first()

fun findEarliestTimestampThatMatchesPattern(busIds: List<String>): Long {
    val busInfo = busIds.mapIndexedNotNull { index, busId ->
        busId.toIntOrNull()
            ?.let { BusInfo(it, index) }
    }

    var earliestTimestamp = 0L
    var lowestCommonMultiple = 1L
    for (i in 2..busInfo.size) {
        earliestTimestamp = generateSequence { lowestCommonMultiple }
            .mapIndexed { index, _ -> earliestTimestamp + (lowestCommonMultiple * index) }
            .filter { possibleTimeValue ->
                allBusesDepartAtCorrectOffset(possibleTimeValue, busInfo.take(i))
            }.first()

        lowestCommonMultiple = busInfo.take(i).fold(1) { a, b -> a * b.busId }
    }

    return earliestTimestamp
}

private fun allBusesDepartAtCorrectOffset(time: Long, busInfo: List<BusInfo>) =
    busInfo.all { (time + it.minutesAfterFirstBus) % it.busId == 0L }

data class BusWaitTime(val busID: Int, val waitTime: Int)

data class BusInfo(val busId: Int, val minutesAfterFirstBus: Int)
