package dev.paulshields.aoc.day4

import dev.paulshields.aoc.common.readFileAsString

fun main() {
    println(" ** Day 4: Passport Processing ** \n")

    val delimiterBetweenPassports = "\n\n"
    val passports = readFileAsString("/day4/Passports.txt")
        .split(delimiterBetweenPassports)
        .map { Passport.parseFromString(it) }

    val numberOfPassportsContainingAllFields = passports
        .filter { it.passportContainsAllFields() }
        .count()

    println("There are $numberOfPassportsContainingAllFields passports containing all fields!")

    val numberOfValidPassports = passports
        .filter { it.passportIsValid() }
        .count()

    println("There are $numberOfValidPassports valid passports!")
}
