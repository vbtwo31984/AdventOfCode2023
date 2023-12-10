package puzzles

class Day9 : Puzzle(9) {
    override fun partOne() {
        val sum = input.sumOf { line ->
            val sequence = line.split(" ").map { it.toLong() }
            getNextInSequence(sequence)
        }
        println("Part 1: $sum")
    }

    override fun partTwo() {
        val sum = input.sumOf { line ->
            val sequence = line.split(" ").map { it.toLong() }
            getPreviousInSequence(sequence)
        }
        println("Part 2: $sum")
    }

    fun getNextInSequence(sequence: List<Long>): Long {
        if (sequence.size == 1) {
            return sequence[0]
        }
        if (sequence.all { it == 0L }) {
            return 0L
        }

        val next = mutableListOf<Long>()
        for (i in 0..sequence.size - 2) {
            next.add(sequence[i + 1] - sequence[i])
        }
        val nextInSequence = getNextInSequence(next)
        return sequence.last() + nextInSequence
    }

    fun getPreviousInSequence(sequence: List<Long>): Long {
        if (sequence.size == 1) {
            return sequence[0]
        }
        if (sequence.all { it == 0L }) {
            return 0L
        }

        val next = mutableListOf<Long>()
        for (i in 0..sequence.size - 2) {
            next.add(sequence[i + 1] - sequence[i])
        }
        val prevInSequence = getPreviousInSequence(next)
        return sequence.first() - prevInSequence
    }
}