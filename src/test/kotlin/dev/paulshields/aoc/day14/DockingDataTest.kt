package dev.paulshields.aoc.day14

import assertk.assertThat
import assertk.assertions.containsAll
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DockingDataTest {
    @Nested
    inner class DecoderChipV1Tests {
        @Test
        fun `should correctly run the sample initialization test`() {
            val program = listOf(
                "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                "mem[8] = 11",
                "mem[7] = 101",
                "mem[8] = 0")

            val memory = runInitializationProgramThroughDecoderChipV1(program)

            assertThat(memory).containsAll(8L to 64L, 7L to 101L)
        }
    }

    @Nested
    inner class DecoderChipV2Tests {
        @Test
        fun `should correctly run the sample initialization test`() {
            val program = listOf(
                "mask = 000000000000000000000000000000X1001X",
                "mem[42] = 100",
                "mask = 00000000000000000000000000000000X0XX",
                "mem[26] = 1")

            val memory = runInitializationProgramThroughDecoderChipV2(program)

            assertThat(memory).containsAll(
                26L to 1L,
                27L to 1L,
                58L to 100L,
                59L to 100L,
                16L to 1L,
                17L to 1L,
                18L to 1L,
                19L to 1L,
                24L to 1L,
                25L to 1L)
        }

        @Test
        fun `should correctly handle handle zeros in the memory mask`() {
            val program = listOf(
                "mask = 000000000000000000000000000000000XXX",
                "mem[8] = 4",
                "mask = XX0000000000000000000000000000000000",
                "mem[0] = 5")

            val memory = runInitializationProgramThroughDecoderChipV2(program)

            assertThat(memory).containsAll(
                8L to 4L,
                9L to 4L,
                10L to 4L,
                11L to 4L,
                12L to 4L,
                13L to 4L,
                14L to 4L,
                15L to 4L,
                0L to 5L,
                17179869184L to 5L,
                34359738368L to 5L,
                51539607552L to 5L)
        }

        @Test
        fun `should correctly handle memory overwrites`() {
            val program = listOf(
                "mask = 000000000000000000000000000000X1001X",
                "mem[26] = 100",
                "mask = 00000000000000000000000000000000X0XX",
                "mem[26] = 1")

            val memory = runInitializationProgramThroughDecoderChipV2(program)

            assertThat(memory).containsAll(
                26L to 1L,
                27L to 1L,
                58L to 100L,
                59L to 100L,
                16L to 1L,
                17L to 1L,
                18L to 1L,
                19L to 1L,
                24L to 1L,
                25L to 1L)
        }

        @Test
        fun `should be able to handle numbers of up to 36 bits in length`() {
            val program = listOf(
                "mask = X00000000000000000000000000000000000",
                "mem[0] = 1000",
                "mem[1] = 1")

            val memory = runInitializationProgramThroughDecoderChipV2(program)

            assertThat(memory).containsAll(
                0L to 1000L,
                34359738368L to 1000L,
                1L to 1L,
                34359738369L to 1L)
        }
    }
}