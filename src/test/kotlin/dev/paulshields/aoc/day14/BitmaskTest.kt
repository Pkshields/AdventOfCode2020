package dev.paulshields.aoc.day14

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class BitmaskTest {
    @Test
    fun `should parse bitmask string`() {
        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"

        val result = Bitmask.fromString(mask)

        assertThat(result.onesMask).isEqualTo(64L)
        assertThat(result.zerosMask).isEqualTo(68719476733L)
    }

    @Test
    fun `should apply bitmask`() {
        val target = Bitmask(1L, 9223372036854775805L)

        val result = target.mask(6L)

        assertThat(result).isEqualTo(5L)
    }

    @Test
    fun `should apply bitmask to a 36 bit number`() {
        val target = Bitmask(17179869184L, 60129542143L)

        val result = target.mask(42949672960L)

        assertThat(result).isEqualTo(51539607552L)
    }

    @Test
    fun `should correctly define the zeroed out bitmask`() {
        assertThat(Bitmask.zero.zerosMask).isEqualTo(0L)
        assertThat(Bitmask.zero.onesMask).isEqualTo(0L)
    }
}