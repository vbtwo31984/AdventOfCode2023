package puzzles

import kotlin.math.pow

class Day4 : Puzzle(4) {
    override fun partOne() {
        val sum = input.sumOf { getPoints(it) }.toInt()
        println("Part 1: $sum")
    }

    override fun partTwo() {
        val cardsMap = createCardMap(input).toMutableMap()
        input.forEachIndexed { index, it ->
            val current = index + 1
            val numberOfWinners = getNumberOfWinners(it)
            for (i in 1..numberOfWinners) {
                if(cardsMap.containsKey(current + i)) {
                    cardsMap[current + i] = cardsMap.getValue(current + i) + cardsMap.getValue(current)
                }
            }
        }
        val num = cardsMap.values.sum()
        println("Part 2: $num")
    }

    fun getYourNumbers(input: String) : List<Int> {
        val start = input.indexOf(':') + 1
        val end = input.indexOf('|')
        val yourNumbers = input.substring(start, end).trim().split(' ').filter { it.isNotBlank() }
        return yourNumbers.map { it.toInt() }
    }

    fun getWinningNumbers(input: String) : List<Int> {
        val start = input.indexOf('|') + 1
        val winningNumbers = input.substring(start).trim().split(' ').filter { it.isNotBlank() }
        return winningNumbers.map { it.toInt() }
    }

    fun getNumberOfWinners(input: String) : Int {
        val yourNumbers = getYourNumbers(input)
        val winningNumbers = getWinningNumbers(input)
        return yourNumbers.count { winningNumbers.contains(it) }
    }

    fun getPoints(input: String) : Double {
        val winningNumbers = getNumberOfWinners(input)
        if (winningNumbers == 0) {
            return 0.0
        }
        return 2.0.pow(winningNumbers - 1)
    }

    fun createCardMap(inputs: Array<String>): Map<Int, Int> {
        return (1..inputs.size).associateWith { 1 }
    }
}