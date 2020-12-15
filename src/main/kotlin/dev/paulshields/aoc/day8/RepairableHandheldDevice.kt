package dev.paulshields.aoc.day8

class RepairableHandheldDevice(bootCode: List<LineOfCode>) : HandheldDevice(bootCode) {
    override fun boot() {
        super.boot()

        executedLinesOfCode
            .map { bootCode[it] }
            .filter { it.operation != CodeOperation.ACC }
            .reversed()
            .first {
                flipOperation(it)
                super.boot()
                flipOperation(it)
                currentState == ExecutionState.FINISHED
            }
    }

    private fun flipOperation(lineOfCode: LineOfCode) = with(lineOfCode) {
        operation = if (operation == CodeOperation.JMP) CodeOperation.NOP else CodeOperation.JMP
    }
}