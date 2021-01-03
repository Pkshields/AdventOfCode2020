package dev.paulshields.aoc.day14

data class Bitmask(val onesMask: Long, val zerosMask: Long) {
    companion object {
        val zero = Bitmask(0, 0)

        fun fromString(input: String) =
            Bitmask(createMaskFromString(input, 0), createMaskFromString(input, 1))

        private fun createMaskFromString(maskString: String, paddingDefault: Long) = maskString
            .replace("X", paddingDefault.toString())
            .toLong(2)
    }

    fun mask(input: Long) = (input or onesMask) and zerosMask
}