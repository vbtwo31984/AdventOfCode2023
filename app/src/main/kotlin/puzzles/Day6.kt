package puzzles

class Day6 : Puzzle(6) {
    val races: Map<Long, Long>
        get() {
            val times = input[0].split(" ").filter { it.isNotBlank() }.drop(1).map { it.toLong() }
            val distances =
                input[1].split(" ").filter { it.isNotBlank() }.drop(1).map { it.toLong() }
            return times.zip(distances).toMap()
        }

    override fun partOne() {
        val result = races.map { (time, distance) ->
            val min = findMin(time, distance)
            val max = time - min
            max - min+1
        }.reduce { acc, i ->  acc * i}
        println("Part 1: $result")
    }

    override fun partTwo() {
        val time = input[0].replace(" ", "").split(":")[1].toLong()
        val distance = input[1].replace(" ", "").split(":")[1].toLong()
        val min = findMin(time, distance)
        val max = time - min
        val numWins = max - min + 1
        println("Part 2: $numWins")
    }

    fun findMin(time: Long, distance: Long): Long {
        val max = time / 2
        val cur = max / 2
        return findMin(cur, 1, max, time, distance)
    }

    private fun findMin(cur: Long, min: Long, max: Long, time: Long, distance: Long): Long {
        val distanceForTime = findDistance(cur, time)

        if (distanceForTime > distance && cur == min) {
            return min
        }
        if (distanceForTime <= distance && max - cur == 1L) {
            return max
        }

        return if (distanceForTime > distance) {
            val newCur = (min + cur) / 2
            findMin(newCur, min, cur, time, distance)
        } else {
            val newCur = (max + cur) / 2
            findMin(newCur, cur + 1, max, time, distance)
        }
    }

    fun findDistance(timeHeld: Long, totalTime: Long) = (totalTime - timeHeld) * timeHeld
}
