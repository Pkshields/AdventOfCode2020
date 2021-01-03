package dev.paulshields.aoc.day14

import assertk.assertThat
import assertk.assertions.containsAll
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class AddressBitmaskTest {
    @Test
    fun `should parse address bitmask string`() {
        val mask = "000000000000000000000000000000X1001X"

        val result = AddressBitmask.fromString(mask)

        assertThat(result.onesMask).isEqualTo(18L)
        assertThat(result.xPositions).containsAll(0, 5)
    }

    @Test
    fun `should apply address bitmask`() {
        val target = AddressBitmask(18L, listOf(0, 5))

        val result = target.mask(42L)

        assertThat(result).containsAll(26L, 27L, 58L, 59L)
    }

    @Test
    fun `should apply address bitmask to a 36 bit number`() {
        val target = AddressBitmask(0L, listOf(35))

        val result = target.mask(0L)

        assertThat(result).containsAll(0L, 34359738368L)
    }

    @Test
    fun `should correctly define the empty address bitmask`() {
        assertThat(AddressBitmask.empty.onesMask).isEqualTo(0L)
        assertThat(AddressBitmask.empty.xPositions).isEqualTo(emptyList())
    }
}