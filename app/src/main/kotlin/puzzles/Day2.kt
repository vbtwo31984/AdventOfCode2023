package puzzles

import kotlin.math.max

class Day2 : Puzzle(2) {
    override fun partOne() {
        val contents = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )
        val sumPossible = input.filter { isPossible(it, contents) }.sumOf { getId(it) }
        println("Part 1: $sumPossible")
    }

    override fun partTwo() {
        val sumPowers = input.map { getMinimum(it) }.map { getPower(it) }.sum()
        println("Part 2: $sumPowers")
    }

    fun isPossible(game: String, contents: Map<String, Int>): Boolean {
        val draws = getDraws(game)
        return draws.all { isDrawPossible(it, contents) }
    }

    fun getDraws(game: String): List<Map<String, Int>> {
        return game.split(";").map { getDrawMap(it) }
    }

    fun getDrawMap(draw: String): Map<String, Int> {
        val regex = Regex("([0-9]+) ([a-z]+)")
        val matches = regex.findAll(draw)
        return matches.associate {
            it.groups[2]!!.value to it.groups[1]!!.value.toInt()
        }
    }

    private fun isDrawPossible(draw: Map<String, Int>, contents: Map<String, Int>): Boolean {
        return draw.all { (color, amount) -> contents[color]!! >= amount }
    }

    fun getId(game: String): Int {
        val regex = Regex("Game ([0-9]+)")
        val match = regex.find(game)
        return match!!.groups[1]!!.value.toInt()
    }

    fun getMinimum(game: String): Map<String, Int> {
        return getDraws(game).reduce { acc, draw ->
            mapOf(
                "green" to max(acc.getOrDefault("green", 0), draw.getOrDefault("green", 0)),
                "blue" to max(acc.getOrDefault("blue", 0), draw.getOrDefault("blue", 0)),
                "red" to max(acc.getOrDefault("red", 0), draw.getOrDefault("red", 0))
            )
        }
    }

    fun getPower(cubes: Map<String, Int>): Int {
        return cubes["green"]!! * cubes["blue"]!! * cubes["red"]!!
    }
}