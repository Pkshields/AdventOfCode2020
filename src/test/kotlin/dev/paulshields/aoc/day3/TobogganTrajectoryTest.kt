package dev.paulshields.aoc.day3

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TobogganTrajectoryTest {
    val map = listOf(
        "#.........",
        ".#........",
        "..#.......",
        "...#......",
        "....#.....",
        ".....#....",
        "......#...",
        ".......#..",
        "........#.",
        ".........#")

    val oneByOnePath = listOf(
        SlopeStep(0, 0),
        SlopeStep(1, 1),
        SlopeStep(2, 2),
        SlopeStep(3, 3),
        SlopeStep(4, 4),
        SlopeStep(5, 5),
        SlopeStep(6, 6),
        SlopeStep(7, 7),
        SlopeStep(8, 8),
        SlopeStep(9, 9))


    val threeBySixPath = listOf(
        SlopeStep(0, 0),
        SlopeStep(6, 3),
        SlopeStep(2, 6),
        SlopeStep(8, 9))

    @Nested
    inner class GenerateSlopePathTests {
        @Test
        fun `should correctly generate a basic path`() {
            val result = generateSlopePath(1, 1, map)

            assertThat(result).isEqualTo(oneByOnePath)
        }

        @Test
        fun `should correctly horizontally wrap the path`() {
            val result = generateSlopePath(3, 6, map)

            assertThat(result).isEqualTo(threeBySixPath)
        }

        @Test
        fun `should handle an empty map`() {
            val result = generateSlopePath(3, 6, emptyList())

            assertThat(result).isEqualTo(emptyList())
        }
    }

    @Nested
    inner class CalculateNumberOfTreesHitTests {
        @Test
        fun `should correctly find all the trees on the path`() {
            val result = oneByOnePath.calculateNumberOfTreesHit(map)

            assertThat(result).isEqualTo(10)
        }

        @Test
        fun `should correctly find a few of the trees on the map`() {
            val result = threeBySixPath.calculateNumberOfTreesHit(map)

            assertThat(result).isEqualTo(1)
        }

        @Test
        fun `should correctly handle an empty path`() {
            val result = emptyList<SlopeStep>().calculateNumberOfTreesHit(map)

            assertThat(result).isEqualTo(0)
        }
    }
}