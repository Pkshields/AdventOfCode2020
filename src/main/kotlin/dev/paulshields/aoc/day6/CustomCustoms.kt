package dev.paulshields.aoc.day6

import dev.paulshields.aoc.common.readFileAsString

fun main() {
    println(" ** Day 6: Customs Customs ** \n")

    val delimiterBetweenCustomsForms = "\n\n"
    val rawAnswersToCustomDeclaration = readFileAsString("/day6/CustomsDeclarationForms.txt")
        ?.split(delimiterBetweenCustomsForms)
        ?: emptyList()

    val customsFormsForPart1 = rawAnswersToCustomDeclaration
        .map(::combineAnswersWhereAnyoneSaidYesIntoCustomsForm)

    println("There were ${countAllAnsweredQuestions(customsFormsForPart1)} questions answered for part 1!")

    val customsFormsForPart2 = rawAnswersToCustomDeclaration
        .map(::combineAnswersWhereEveryoneSaidYesIntoCustomsForm)

    println("There were ${countAllAnsweredQuestions(customsFormsForPart2)} questions answered for part 2!")
}

fun combineAnswersWhereAnyoneSaidYesIntoCustomsForm(input: String) = input
    .replace("\n", "")
    .toSet()
    .sorted()
    .joinToString("")

fun combineAnswersWhereEveryoneSaidYesIntoCustomsForm(input: String): String {
    val eachPersonsAnswers = input
        .trim()
        .lines()

    return eachPersonsAnswers
        .joinToString("")
        .toSet()
        .filter { everyoneAnsweredYesToQuestion(eachPersonsAnswers, it) }
        .sorted()
        .joinToString("")
}

private fun everyoneAnsweredYesToQuestion(eachPersonsAnswers: List<String>, question: Char) = eachPersonsAnswers
    .count { it.contains(question) } == eachPersonsAnswers.count()

fun countAllAnsweredQuestions(customsForms: List<String>) = customsForms
    .joinToString("")
    .count()