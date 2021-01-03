package dev.paulshields.aoc.day14

import dev.paulshields.aoc.common.allIndexesOf
import dev.paulshields.aoc.common.bitAt
import dev.paulshields.aoc.common.pow

data class AddressBitmask(val onesMask: Long, val xPositions: List<Int>) {
    companion object {
        val empty = AddressBitmask(0L, emptyList())

        fun fromString(input: String): AddressBitmask {
            val onesMask = input.replace("X", "0").toLong(2)
            val xPositions = input.reversed().allIndexesOf('X')
            return AddressBitmask(onesMask, xPositions)
        }
    }

    private val countOfAllPossibleVariationsOfAddress = 2L.pow(xPositions.size)

    fun mask(input: Long): List<Long> {
        val baseAddress = input or onesMask

        return (0 until countOfAllPossibleVariationsOfAddress)
            .map { availableBits ->
                var address = baseAddress
                xPositions.forEachIndexed { index, xPosition ->
                    when(availableBits.bitAt(index)) {
                        0 -> address = forceBitToBeZero(address, xPosition)
                        1 -> address = forceBitToBeOne(address, xPosition)
                    }
                }
                address
            }
            .toList()
    }

    private fun forceBitToBeZero(addressToMask: Long, index: Int) =
        addressToMask and (Long.MAX_VALUE - 2L.pow(index))

    private fun forceBitToBeOne(addressToMask: Long, index: Int) =
        addressToMask or (1L shl index)
}
