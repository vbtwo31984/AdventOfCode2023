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

        val numInside = input.mapIndexed { y, row ->
            val filtered = row.filterIndexed { x, _ ->
                isInside(y to x, loop)
            }
            filtered.length
        }.sum()

        println("Part 2: $numInside")
        drawing.forEach { row ->
            row.forEach {
                print(it)
            }
            println()
        }
    }

    private fun findStart(): Pair<Int, Int> {
        for (y in input.indices) {
            val x = input[y].indexOf('S')
            if (x != -1) {
                return y to x
            }
        }
        throw IllegalArgumentException()
    }

    private fun findNext(cur: Pair<Int, Int>, prev: Pair<Int, Int>): Pair<Int, Int> {
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

    private fun createDrawing(): MutableList<MutableList<Char>> {
        return input.map {
            it.toCharArray().map { '.' }.toMutableList()
        }.toMutableList()
    }

    private fun isInside(coord: Pair<Int, Int>, loop: List<Pair<Int, Int>>): Boolean {
        setOf('│', '└', '┘', '┐', '┌')
        var nextWall: Char? = null
        val loopSet = loop.toSet()
        var hits = 0
        if (coord !in loopSet) {
            val y = coord.first
            for (x in coord.second + 1..<input[y].length) {
                val value = input[y][x]
                val inLoop = loop.contains(y to x)
                if (inLoop && nextWall != null) {
                    if (value == '─') continue
                    if (value == nextWall)
                        hits++

                    nextWall = null
                } else if (inLoop) {
                    if (value == '┌') nextWall = '┘'
                    else if (value == '└') nextWall = '┐'
                    else if (value != '─') hits++
                }
            }
        }
        return hits % 2 == 1
    }
}