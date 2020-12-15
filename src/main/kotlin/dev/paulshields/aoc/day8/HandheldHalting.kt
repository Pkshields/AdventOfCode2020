package dev.paulshields.aoc.day8

import dev.paulshields.aoc.common.readFileAsString

fun main() {
    println(" ** Day 8: Handheld Halting ** \n")

    val rawBootCode = readFileAsString("/day8/HandheldDeviceBootCode.txt")
    val bootCode = LineOfCode.parseAll(rawBootCode)

    val handheldDevice = HandheldDevice(bootCode)
    handheldDevice.boot()

    println("The accumulator was set to ${handheldDevice.accumulator} when a boot loop was detected!")

    val repairableHandheldDevice = RepairableHandheldDevice(bootCode)
    repairableHandheldDevice.boot()

    println("After repairing the boot code the accumulator was set to ${repairableHandheldDevice.accumulator}!")
}
