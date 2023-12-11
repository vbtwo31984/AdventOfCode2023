package puzzles

class Day10 : Puzzle(10) {
    override fun partOne() {
        val start = findStart()
        val one = mutableListOf(start, start.first - 1 to start.second)
        val two = mutableListOf(start, start.first + 1 to start.second)
        var steps = 0

        while (one.last() != two.last()) {
            one.add(findNext(one.last(), one[steps]))
            two.add(findNext(two.last(), two[steps]))
            steps++
        }
        println("Part 1: ${steps + 1}")
    }

    override fun partTwo() {
        val drawing = createDrawing()
        val start = findStart()
        var steps = 0
        val loop = mutableListOf(start, start.first - 1 to start.second)

        while (loop.last() != start) {
            loop.add(findNext(loop.last(), loop[steps]))
            steps++
        }

        loop.forEach {
            drawing[it.first][it.second] = input[it.first][it.second]
        }
        println("Part 2:")
        drawing.forEach { row ->
            row.forEach {
                print(it)
            }
            println()
        }
    }

    fun findStart(): Pair<Int, Int> {
        for (y in input.indices) {
            val x = input[y].indexOf('S')
            if (x != -1) {
                return y to x
            }
        }
        throw IllegalArgumentException()
    }

    fun findNext(cur: Pair<Int, Int>, prev: Pair<Int, Int>): Pair<Int, Int> {
        val curChar = input[cur.first].elementAt(cur.second)
        val possibilities = when (curChar) {
            '│' -> listOf(cur.first - 1 to cur.second, cur.first + 1 to cur.second)
            '─' -> listOf(cur.first to cur.second - 1, cur.first to cur.second + 1)
            '└' -> listOf(cur.first - 1 to cur.second, cur.first to cur.second + 1)
            '┘' -> listOf(cur.first - 1 to cur.second, cur.first to cur.second - 1)
            '┐' -> listOf(cur.first to cur.second - 1, cur.first + 1 to cur.second)
            '┌' -> listOf(cur.first to cur.second + 1, cur.first + 1 to cur.second)
            else -> listOf()
        }
        return possibilities.find { it != prev }!!
    }

    fun createDrawing(): MutableList<MutableList<Char>> {
        return input.map {
            it.toCharArray().map { '.' }.toMutableList()
        }.toMutableList()
    }
}