package dev.paulshields.aoc.day4

data class Passport(
    private val birthYear: String?,
    private val issueYear: String?,
    private val expirationYear: String?,
    private val height: String?,
    private val hairColor: String?,
    private val eyeColor: String?,
    private val passportId: String?) {

    companion object {
        private val eyeColorOptions = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

        private val passportKeyValueRegex = Regex("(\\w{3}):([\\w#]+)")
        private val heightRegex = Regex("(\\d+)(cm|in)")
        private val hairColorRegex = Regex("#[0-9a-f]{6}")

        fun parseFromString(rawPassport: String): Passport {
            val passportData = passportKeyValueRegex
                .findAll(rawPassport)
                .map { it.destructured.run { Pair(component1(), component2()) } }
                .toMap()

            return with(passportData) {
                Passport(get("byr"), get("iyr"), get("eyr"), get("hgt"), get("hcl"), get("ecl"), get("pid"))
            }
        }
    }

    fun passportContainsAllFields() = birthYear != null
            && issueYear != null
            && expirationYear != null
            && height != null
            && hairColor != null
            && eyeColor != null
            && passportId != null

    fun passportIsValid() = birthYearIsValid()
            && issueYearIsValid()
            && expirationYearIsValid()
            && heightIsValid()
            && hairColorIsValid()
            && eyeColorIsValid()
            && passportIdIsValid()

    private fun birthYearIsValid() = birthYear?.toIntOrNull()?.let { it in 1920..2002 } ?: false

    private fun issueYearIsValid() = issueYear?.toIntOrNull()?.let { it in 2010..2020 } ?: false

    private fun expirationYearIsValid() = expirationYear?.toIntOrNull()?.let { it in 2020..2030 } ?: false

    private fun heightIsValid() = height?.let {
        val parsedHeight = heightRegex.find(it)
        val heightValue = parsedHeight?.destructured?.component1()?.toInt()
        val heightUnits = parsedHeight?.destructured?.component2()

        if (heightUnits?.contains("cm") == true) {
            heightValue in 150..193
        } else {
            heightValue in 59..76
        }
    } ?: false

    private fun hairColorIsValid() = hairColor?.let { hairColorRegex.matches(it) } ?: false

    private fun eyeColorIsValid() = eyeColorOptions.contains(eyeColor)

    private fun passportIdIsValid() = passportId?.let { it.length == 9 && it.toIntOrNull() != null } ?: false
}
