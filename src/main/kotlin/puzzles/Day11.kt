package puzzles

class Day11 : Puzzle(11) {
    override fun partOne() {
        val galaxyPairs = findGalaxyPairs()
        val sum =
            galaxyPairs.sumOf {
                Math.abs(it.first.x - it.second.x).toLong() + Math.abs(it.first.y - it.second.y)
                    .toLong()
            }
        println("Part 1: $sum")
    }

    override fun partTwo() {
        TODO("Not yet implemented")
    }

    fun expandUniverse(): List<List<Char>> {
        val result = mutableListOf<MutableList<Char>>()
        for (i in input.indices) {
            result.add(input[i].toMutableList())
            if (input[i].all { it == '.' }) {
                result.add(input[i].toMutableList())
            }
        }
        var added = 0
        for (i in input[0].indices) {
            if (input.all { it[i] == '.' }) {
                result.map { it.add(i + added, '.') }
                added++
            }
        }
        return result
    }

    fun findGalaxies(): List<Point> {
        val expanded = expandUniverse()
        val result = mutableListOf<Point>()
        for (i in expanded.indices) {
            for (j in expanded[i].indices) {
                if (expanded[i][j] == '#') {
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
