package dev.paulshields.aoc.day7

import dev.paulshields.aoc.common.readFileAsStringList

val bagRuleRegex = Regex("(.+?) bags? contain (.+)")
val captureInnerBagDetailsRegex = Regex("(\\d) (.+?) bags?")

fun main() {
    println(" ** Day 7: Handy Haversacks ** \n")

    val input = readFileAsStringList("/day7/BagRules.txt")
    val bagRules = parseBagRules(input)

    val bagsThatCanHoldShinyGoldBag = findBagsThatCanHold("shiny gold", bagRules)

    println("There are ${bagsThatCanHoldShinyGoldBag.size} bag colors that can hold a shiny gold bag!")

    val numberOfBagsWithinShinyGoldBag = countBagsRequiredToBeContainedInsideBag("shiny gold", bagRules)

    println("There are $numberOfBagsWithinShinyGoldBag bags within a shiny gold bag!")
}

fun parseBagRules(rawRules: List<String>) = rawRules
    .filter { !it.contains("no other bags") }
    .mapNotNull(::parseSingleBagRule)

private fun parseSingleBagRule(rawRule: String) = bagRuleRegex
    .find(rawRule)
    ?.let { bagRuleMatch ->
        val innerBags = captureInnerBagDetailsRegex
            .findAll(bagRuleMatch.groupValues[2])
            .map { BagRuleDetails(it.groupValues[2], it.groupValues[1].toInt()) }
            .toList()

        BagRule(bagRuleMatch.groupValues[1], innerBags)
    }

fun findBagsThatCanHold(bag: String, bagRules: List<BagRule>): Set<BagRule> {
    val outerBagsThatCanHoldRequestedBag = bagRules
        .filter { bagRule -> bagRule.innerBags.any { it.bagColor == bag } }
        .toSet()

    val bagsThatCanHoldOuterBags = outerBagsThatCanHoldRequestedBag
        .flatMap { findBagsThatCanHold(it.outerBag, bagRules) }

    return outerBagsThatCanHoldRequestedBag.union(bagsThatCanHoldOuterBags)
}

fun countBagsRequiredToBeContainedInsideBag(bag: String, bagRules: List<BagRule>): Int = bagRules
    .firstOrNull { it.outerBag.contains(bag) }
    ?.let { bagRule ->
        val numberOfNestedBags = bagRule
            .innerBags
            .sumOf { countBagsRequiredToBeContainedInsideBag(it.bagColor, bagRules) * it.count }
        numberOfNestedBags + bagRule.innerBags.sumBy { it.count }
    } ?: 0

data class BagRule(val outerBag: String, val innerBags: List<BagRuleDetails>)
data class BagRuleDetails(val bagColor: String, val count: Int)