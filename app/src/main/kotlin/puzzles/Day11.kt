package puzzles

class Day11 : Puzzle(11) {
    val galaxyPairs = findGalaxyPairs()
    override fun partOne() {
        val sum = getSum(2)
        println("Part 1: $sum")
    }

    override fun partTwo() {
        val sum = getSum(1000000)
        println("Part 2: $sum")
    }

    private fun getSum(expandTo: Int): Long {
        val expandedX = expandX(expandTo)
        val expandedY = expandY(expandTo)
        val sum =
            galaxyPairs.sumOf {
                var path = 0L
                val minY = it.first.y.coerceAtMost(it.second.y)
                val maxY = it.first.y.coerceAtLeast(it.second.y)
                for (y in minY..<maxY) {
                    path += expandedY[y]
                }
                val minX = it.first.x.coerceAtMost(it.second.x)
                val maxX = it.first.x.coerceAtLeast(it.second.x)
                for (x in minX..<maxX) {
                    path += expandedX[x]
                }
                path
            }
        return sum
    }

    fun expandY(to: Int): List<Int> {
        return input.map { y ->
            if (y.all { it == '.' }) to
            else 1
        }
    }

    fun expandX(to: Int): List<Int> {
        return input[0].mapIndexed { x, _ ->
            if (input.all { it[x] == '.' }) to
            else 1
        }
    }

    fun findGalaxies(): List<Point> {
        val result = mutableListOf<Point>()
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == '#') {
                    result.add(Point(i, j))
                }
            }
        }
        return result
    }

    fun findGalaxyPairs(): Set<Pair<Point, Point>> {
        val galaxies = findGalaxies()

        val grouped = mutableSetOf<Pair<Point, Point>>()
        for (i in galaxies.indices) {
            for (j in galaxies.indices) {
                val one = galaxies[i] to galaxies[j]
                val two = galaxies[j] to galaxies[i]
                if (i != j && one !in grouped && two !in grouped) {
                    grouped.add(one)
                }
            }
        }


        return grouped
    }
}

class Point(val y: Int, val x: Int)
