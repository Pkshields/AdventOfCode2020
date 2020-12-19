package dev.paulshields.aoc.day11

abstract class SeatPredictor(input: String, private val occupiedSeatTolerance: Int) {
    private var seatingLayout = input
        .lines()
        .filter { it.isNotEmpty() }
        .map { it.map(Seat::parseChar).toList() }
        .toList()

    protected val adjacentSeats = listOf(-1 to -1, -1 to 0, -1 to 1,
                                          0 to -1,           0 to 1,
                                          1 to -1,  1 to 0,  1 to 1)

    fun iterateUntilNoMoreSeatsChange(): Int {
        val seatCounts = mutableListOf(countOccupiedSeats())
        do {
            nextRound()
            seatCounts.add(countOccupiedSeats())
        } while (seatCounts.last() != seatCounts[seatCounts.size - 2])
        return seatCounts.last()
    }

    private fun nextRound() {
        seatingLayout = seatingLayout.mapIndexed { y, row ->
            row.mapIndexed { x, seat -> updateSeat(x, y, seat) }
        }
    }

    private fun updateSeat(x: Int, y: Int, seat: Seat) = when(seat) {
        Seat.EMPTY -> if (countOccupiedAdjacentSeats(x, y) == 0) Seat.OCCUPIED else Seat.EMPTY
        Seat.OCCUPIED -> if (countOccupiedAdjacentSeats(x, y) >= occupiedSeatTolerance) Seat.EMPTY else Seat.OCCUPIED
        else -> Seat.FLOOR
    }

    private fun countOccupiedAdjacentSeats(x: Int, y: Int) =
        calculateSeatsToCheck(x, y)
            .mapNotNull(::getSeat)
            .filter { seat -> seat == Seat.OCCUPIED }
            .count()

    protected abstract fun calculateSeatsToCheck(x: Int, y: Int): List<Pair<Int, Int>>

    protected fun getSeat(position: Pair<Int, Int>) =
        position.let { (x, y) -> seatingLayout.getOrNull(y)?.getOrNull(x) }

    private fun countOccupiedSeats() = seatingLayout.sumBy { row -> row.filter { it == Seat.OCCUPIED }.count() }
}