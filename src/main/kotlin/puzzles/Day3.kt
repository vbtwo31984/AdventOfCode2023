package puzzles

import java.util.stream.IntStream.range

class Day3 : Puzzle(3) {
    override fun partOne() {
        val sumInputs = input.mapIndexed { index, line ->
            val numbersWithCoords = getNumberWithCoord(line)
            numbersWithCoords.filter {
                isPartNumber(it.first, index, it.second, input)
            }.sumOf { it.first }
        }.sum()
        println("Part 1: $sumInputs")
    }

    override fun partTwo() {
        val output = input.flatMapIndexed { index, line ->
            val numbersWithCoords = getNumberWithCoord(line)
            numbersWithCoords.flatMap { num ->
                getPossibleGears(num.first, index, num.second, input).map {
                    it to num.first
                }
            }
        }.groupBy({ it.first }, { it.second })
            .filter { it.value.size == 2 }
            .mapValues { it.value[0] * it.value[1] }
            .values.sum()
        println("Part 2: $output")
    }

    fun getNumberWithCoord(line: String): Sequence<Pair<Int, Int>> {
        val regex = Regex("[0-9]+")
        val matches = regex.findAll(line)
        return matches.map { it.value.toInt() to it.range.first }
    }

    fun getPossibleGears(value: Int, x: Int, y: Int, lines: Array<String>): List<Pair<Int, Int>> {
        val coords = getPossibleCoords(value, x, y)
        return coords
            .filter { it.first >= 0 && it.first < lines.size }
            .filter { it.second >= 0 && it.second < lines[it.first].length }
            .filter { lines[it.first][it.second] == '*' }
    }

    fun isPartNumber(value: Int, x: Int, y: Int, lines: Array<String>): Boolean {
        val coords = getPossibleCoords(value, x, y)
        return coords.any { isPart(it, lines) }
    }

    fun isPart(coord: Pair<Int, Int>, input: Array<String>): Boolean {
        if (coord.first < 0 || coord.first >= input.size) {
            return false
        }
        val line = input[coord.first]
        if (coord.second < 0 || coord.second >= line.length) {
            return false
        }
        val value = line[coord.second]
        return isPart(value)
    }

    fun isPart(value: Char): Boolean {
        return !value.isDigit() && value != '.'
    }

    fun getPossibleCoords(value: Int, x: Int, y: Int): List<Pair<Int, Int>> {
        val startY = y - 1;
        val endY = y + value.toString().length
        val coords = mutableListOf<Pair<Int, Int>>()
        range(startY, endY + 1).forEach {
            coords.add(x - 1 to it)
        }
        coords.add(x to startY)
        coords.add(x to endY)
        range(startY, endY + 1).forEach {
            coords.add(x + 1 to it)
        }
        return coords
    }
}