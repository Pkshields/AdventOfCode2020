package dev.paulshields.aoc.day7

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class HandyHaversacksTest {
    private val topLevelBagColor = "light red"
    private val midLevelBagColor = "dark orange"
    private val bottomLevelBagColor = "bright white"
    private val bagColorThatDoesNotExist = "very maroon"

    private val topLevelBag = BagRule(topLevelBagColor, listOf(
        BagRuleDetails("bright white", 1), BagRuleDetails(midLevelBagColor, 2)))
    private val midLevelBag = BagRule(midLevelBagColor, listOf(BagRuleDetails(bottomLevelBagColor, 3)))
    private val bottomLevelBag = BagRule(bottomLevelBagColor, listOf(BagRuleDetails("shiny gold", 1)))

    private val bagRules = listOf(topLevelBag, midLevelBag, bottomLevelBag)

    @Nested
    inner class ParseBagRulesTests {
        @Test
        fun `should parse bag rules correctly`() {
            val input = listOf("light red bags contain 1 bright white bag, 2 dark orange bags.",
                "dark orange bags contain 3 bright white bags.",
                "bright white bags contain 1 shiny gold bag.")

            val result = parseBagRules(input)

            assertThat(result).isEqualTo(bagRules)
        }

        @Test
        fun `should handle empty lists when parsing rules`() {
            val result = parseBagRules(emptyList())

            assertThat(result).isEqualTo(emptyList())
        }

        @Test
        fun `should remove rules without any inner bags when parsing rules`() {
            val input = listOf("dim violet bags contain no other bags.")

            val result = parseBagRules(input)

            assertThat(result).isEqualTo(emptyList())
        }
    }

    @Nested
    inner class FindBagsThatCanHoldTests {
        @Test
        fun `should correctly find bag that can hold a bag color`() {
            val result = findBagsThatCanHold(midLevelBagColor, bagRules)

            assertThat(result.size).isEqualTo(1)
            assertThat(result).contains(topLevelBag)
        }

        @Test
        fun `should correctly find all bags that can hold a bag color`() {
            val result = findBagsThatCanHold(bottomLevelBagColor, bagRules)

            assertThat(result.size).isEqualTo(2)
            assertThat(result).contains(topLevelBag)
            assertThat(result).contains(midLevelBag)
        }

        @Test
        fun `should handle bag that does not exist in the rules when trying to find a bag that can hold a bag color`() {
            val result = findBagsThatCanHold(bagColorThatDoesNotExist, bagRules)

            assertThat(result).isEmpty()
        }

        @Test
        fun `should handle no bag rules trying to find a bag that can hold a bag color`() {
            val result = findBagsThatCanHold(bagColorThatDoesNotExist, emptyList())

            assertThat(result).isEmpty()
        }
    }

    @Nested
    inner class CountBagsRequiredToBeContainedInsideBagTests {
        @Test
        fun `should correctly count number of bags that are required to be contained with a specified bag`() {
            val expectedResult = 16

            val result = countBagsRequiredToBeContainedInsideBag(topLevelBagColor, bagRules)

            assertThat(result).isEqualTo(expectedResult)
        }

        @Test
        fun `should handle bag that does not exist in the rules when trying to find a bag that can hold a bag color`() {
            val result = countBagsRequiredToBeContainedInsideBag(bagColorThatDoesNotExist, bagRules)

            assertThat(result).isEqualTo(0)
        }

        @Test
        fun `should handle no bag rules trying to find a bag that can hold a bag color`() {
            val result = countBagsRequiredToBeContainedInsideBag(bagColorThatDoesNotExist, emptyList())

            assertThat(result).isEqualTo(0)
        }
    }
}