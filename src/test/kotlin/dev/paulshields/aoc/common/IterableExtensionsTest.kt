package dev.paulshields.aoc.common

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class IterableExtensionsTest {
    val sampleData = listOf(10, 11, 12, 13, 14)

    @Nested
    inner class FirstIndexedOrNullTests {
        @Test
        fun `should iterate through all elements with their index`() {
            val iteratedData = mutableListOf<Pair<Int, Int>>()

            sampleData.firstIndexedOrNull { index, item ->
                iteratedData.add(index to item)
                false
            }

            assertThat(iteratedData).containsOnly(
                0 to 10,
                1 to 11,
                2 to 12,
                3 to 13,
                4 to 14)
        }

        @Test
        fun `should stop iterating and return correct item when true`() {
            val iteratedData = mutableListOf<Pair<Int, Int>>()

            val output = sampleData.firstIndexedOrNull { index, item ->
                iteratedData.add(index to item)
                true
            }

            assertThat(iteratedData).containsOnly(0 to 10)
            assertThat(output).isEqualTo(10)
        }

        @Test
        fun `should return null when predicate never succeeds`() {
            val output = sampleData.firstIndexedOrNull { _, _ -> false }

            assertThat(output).isNull()
        }
    }

    @Nested
    inner class FirstIndexedTests {
        @Test
        fun `should match functionality`() {
            val iteratedData = mutableListOf<Pair<Int, Int>>()

            val output = sampleData.firstIndexed { index, item ->
                iteratedData.add(index to item)
                index == 4
            }

            assertThat(iteratedData).containsOnly(
                0 to 10,
                1 to 11,
                2 to 12,
                3 to 13,
                4 to 14)
            assertThat(output).isEqualTo(14)
        }

        @Test
        fun `should throw when predicate never succeeds`() {
            assertThrows<NoSuchElementException> {
                sampleData.firstIndexed { _, _ -> false }
            }
        }
    }
}