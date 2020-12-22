package dev.paulshields.aoc.day12

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class RealEvasiveActionHandlerTest {
    @Test
    fun `should correctly simulate the distance the ship travels`() {
        val input = "F10\nN3\nF7\nR90\nF11"
        val target = RealEvasiveActionHandler(input)

        val result = target.calculateManhattanDistance()

        assertThat(result).isEqualTo(286)
    }

    @Test
    fun `should handle empty strings`() {
        val target = AssumedEvasiveActionHandler("")

        val result = target.calculateManhattanDistance()

        assertThat(result).isEqualTo(0)
    }
}