package dev.paulshields.aoc.common

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

class FileHelpersTest {
    private val testFileLocation = "/FileHelpersTestFile.txt"
    private val brokenFileLocation = "/FilethatDoesNotExist.txt"
    private val contentsOfTestFile = "2020 was not a good year!"

    @Test
    fun `should read contents of file`() {
        val contents = readFileAsString(testFileLocation)

        assertThat(contents).isEqualTo(contentsOfTestFile)
    }

    @Test
    fun `should handle file not found`() {
        val contents = readFileAsString(brokenFileLocation)

        assertThat(contents).isNull()
    }
}