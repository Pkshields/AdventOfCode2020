package dev.paulshields.aoc.day4

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import dev.paulshields.aoc.testcommon.parameterizedTest
import dev.paulshields.aoc.testcommon.runTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class PassportTest {
    val validPassport = buildPassport()
    val passportWithNullData = buildPassport(null, null, null, null, null, null, null)
    val passportWithEmptyFields = buildPassport("", "", "", "", "", "", "")

    @Nested
    inner class ParseFromStringTests {
        @Test
        fun `should correctly create a passport from a valid string`() {
            val validString = "hgt:72in ecl:blu\n" +
                    "eyr:2030 hcl:#000000 iyr:2020\n" +
                    "pid:123456789 byr:1992"

            val result = Passport.parseFromString(validString)

            assertThat(result).isEqualTo(validPassport)
        }
        @Test
        fun `should correctly create an empty passport from an invalid string`() {
            val invalidString = "could be anything here, just not any real data"

            val result = Passport.parseFromString(invalidString)

            assertThat(result).isEqualTo(passportWithNullData)
        }
    }

    @Nested
    inner class PassportContainsAllFieldsTests {
        @Test
        fun `should correctly pass a valid passport`() {
            val result = validPassport.passportContainsAllFields()

            assertThat(result).isTrue()
        }

        @Test
        fun `should correctly pass a passport that contains all fields, but with invalid data`() {
            val result = passportWithEmptyFields.passportContainsAllFields()

            assertThat(result).isTrue()
        }

        @Test
        fun `should correctly fail a passport that contains no data`() {
            val result = passportWithNullData.passportContainsAllFields()

            assertThat(result).isFalse()
        }
    }

    @Nested
    inner class ValidationTests {
        @Test
        fun `should pass for a valid passport`() {
            val result = validPassport.passportIsValid()

            assertThat(result).isTrue()
        }

        @TestFactory
        fun `should fail for an invalid birth year`() = parameterizedTest(
            "1919", "9", "2003", "3000"
        ).runTest { input ->
            val passport = buildPassport(birthYear = input)

            val result = passport.passportIsValid()

            assertThat(result).isFalse()
        }

        @TestFactory
        fun `should fail for an invalid issue year`() = parameterizedTest(
            "2009", "9", "2021", "3000"
        ).runTest { input ->
            val passport = buildPassport(issueYear = input)

            val result = passport.passportIsValid()

            assertThat(result).isFalse()
        }

        @TestFactory
        fun `should fail for an invalid expiration year`() = parameterizedTest(
            "2019", "9", "2031", "3000"
        ).runTest { input ->
            val passport = buildPassport(expirationYear = input)

            val result = passport.passportIsValid()

            assertThat(result).isFalse()
        }

        @TestFactory
        fun `should pass for valid heights`() = parameterizedTest(
            "59in", "76in", "65in", "150cm", "193cm", "180cm"
        ).runTest { input ->
            val passport = buildPassport(height = input)

            val result = passport.passportIsValid()

            assertThat(result).isTrue()
        }

        @TestFactory
        fun `should fail for an invalid height`() = parameterizedTest(
            "58ft", "77ft", "400ft", "1ft", "149cm", "194cm", "5cm", "600cm",
        ).runTest { input ->
            val passport = buildPassport(height = input)

            val result = passport.passportIsValid()

            assertThat(result).isFalse()
        }

        @TestFactory
        fun `should fail for an invalid hair color`() = parameterizedTest(
            "000000", "ffffff", "any text"
        ).runTest { input ->
            val passport = buildPassport(hairColor = input)

            val result = passport.passportIsValid()

            assertThat(result).isFalse()
        }

        @TestFactory
        fun `should fail for an invalid eye color`() = parameterizedTest(
            "qft", "lol", "any text"
        ).runTest { input ->
            val passport = buildPassport(eyeColor = input)

            val result = passport.passportIsValid()

            assertThat(result).isFalse()
        }

        @TestFactory
        fun `should fail for an invalid passport id`() = parameterizedTest(
            "12345678", "1234567890", "any text"
        ).runTest { input ->
            val passport = buildPassport(eyeColor = input)

            val result = passport.passportIsValid()

            assertThat(result).isFalse()
        }
    }

    private fun buildPassport(
        birthYear: String? = "1992",
        issueYear: String? = "2020",
        expirationYear: String? = "2030",
        height: String? = "72in",
        hairColor: String? = "#000000",
        eyeColor: String? = "blu",
        passportId: String? = "123456789") = Passport(
        birthYear,
        issueYear,
        expirationYear,
        height,
        hairColor,
        eyeColor,
        passportId)
}