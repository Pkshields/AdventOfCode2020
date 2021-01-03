package dev.paulshields.aoc.day14

import dev.paulshields.aoc.common.readFileAsStringList

val valueSetCaptureRegex = Regex("mem\\[(\\d+)] = (\\d+)")

fun main() {
    val program = readFileAsStringList("/day14/InitializationProgram.txt")

    var memory = runInitializationProgramThroughDecoderChipV1(program)
    println("The sum of all data in memory is ${memory.values.sum()}!")

    memory = runInitializationProgramThroughDecoderChipV2(program)
    println("The sum of all data in memory is ${memory.values.sum()}!")
}

fun runInitializationProgramThroughDecoderChipV1(program: List<String>): MutableMap<Long, Long> {
    val memory = mutableMapOf<Long, Long>()
    var bitmask = Bitmask.zero

    for (line in program) {
        if (lineIsBitmaskString(line)) {
            bitmask = Bitmask.fromString(line.drop(7))
        } else {
            valueSetCaptureRegex
                .find(line)
                ?.groupValues
                ?.let {
                    memory[it.component2().toLong()] = bitmask.mask(it.component3().toLong())
                }
        }
    }

    return memory
}

fun runInitializationProgramThroughDecoderChipV2(program: List<String>): MutableMap<Long, Long> {
    val memory = mutableMapOf<Long, Long>()
    var bitmask = AddressBitmask.empty

    for (line in program) {
        if (lineIsBitmaskString(line)) {
            bitmask = AddressBitmask.fromString(line.drop(7))
        } else {
            valueSetCaptureRegex
                .find(line)
                ?.groupValues
                ?.let { loc ->
                    bitmask
                        .mask(loc.component2().toLong())
                        .forEach { memory[it] = loc.component3().toLong() }
                }
        }
    }

    return memory
}

private fun lineIsBitmaskString(input: String) = input.startsWith("mask")
