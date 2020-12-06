package dev.paulshields.aoc.day2

import dev.paulshields.aoc.common.readFileAsStringList

fun main() {
    println(" ** Day 2: Password Philosophy ** \n")

    val passwordEntries = readFileAsStringList("/day2/PasswordsDatabase.txt")
        .mapNotNull(::parsePasswordEntry)

    val firstValidPasswordsCount = passwordEntries
        .filter(::passwordIsValidByTheCharCountRule)
        .count()

    println("There are $firstValidPasswordsCount passwords in the database according to the char count rule!")

    val secondValidPasswordsCount = passwordEntries
        .filter(::passwordIsValidByTheCharPositionRule)
        .count()

    println("There are $secondValidPasswordsCount passwords in the database according to the char position rule!")
}

fun parsePasswordEntry(rawPasswordEntry: String): PasswordEntry? {
    val passwordEntryRegex = Regex("(\\d+)-(\\d+) (\\w): (\\w+)")
    val regexResult = passwordEntryRegex.find(rawPasswordEntry)

    return regexResult
        ?.destructured
        ?.let {
            PasswordEntry(
                it.component3()[0],
                it.component1().toInt(),
                it.component2().toInt(),
                it.component4())
        }
}

fun passwordIsValidByTheCharCountRule(passwordEntry: PasswordEntry) = with(passwordEntry) {
    val countOfRequiredChar = password.count { it == requiredCharacter }
    countOfRequiredChar in minimum..maximum
}

fun passwordIsValidByTheCharPositionRule(passwordEntry: PasswordEntry) = with(passwordEntry) {
    val firstChar = password[minimum - 1]
    val secondChar = password[maximum - 1]

    (firstChar == requiredCharacter && secondChar != requiredCharacter) ||
            (firstChar != requiredCharacter && secondChar == requiredCharacter)
}

data class PasswordEntry(
    val requiredCharacter: Char,
    val minimum: Int,
    val maximum: Int,
    val password: String)
