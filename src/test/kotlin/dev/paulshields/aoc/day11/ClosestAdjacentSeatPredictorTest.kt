package dev.paulshields.aoc.day11

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class ClosestAdjacentSeatPredictorTest {
    @Test
    fun `should correctly simulate using the closest adjacent seat algorithm until number of occupied seats no longer changes`() {
        val input = "L.LL.LL.LL\n" +
                "LLLLLLL.LL\n" +
                "L.L.L..L..\n" +
                "LLLL.LL.LL\n" +
                "L.LL.LL.LL\n" +
                "L.LLLLL.LL\n" +
                "..L.L.....\n" +
                "LLLLLLLLLL\n" +
                "L.LLLLLL.L\n" +
                "L.LLLLL.LL"
        val target = ClosestAdjacentSeatPredictor(input)

        val result = target.iterateUntilNoMoreSeatsChange()

        assertThat(result).isEqualTo(26)
    }
    @Test
    fun `should handle empty strings`() {
        val target = AdjacentSeatPredictor("")

        val result = target.iterateUntilNoMoreSeatsChange()

        assertThat(result).isEqualTo(0)
    }
}