package dev.paulshields.aoc.common

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEmpty
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test
import java.util.Stack

class ListExtensionsTest {
    @Test
    fun `should create stack from list`() {
        val input = listOf("some", "input", "strings")

        val result = input.toStack()

        assertThat(result).containsOnly(input[0], input[1], input[2])
        assertThat(result).isInstanceOf(Stack<String>().javaClass)
    }
    @Test
    fun `should handle empty input when creating stack from list`() {
        val result = emptyList<String>().toStack()

        assertThat(result).isEmpty()
    }
}