package puzzles

class Day8 : Puzzle(8) {
    private val steps = input[0]
    private val network = parseNetwork(input.copyOfRange(2, input.size))

    override fun partOne() {
        val numSteps = getNumSteps(steps, network)
        println("Part 1: $numSteps")
    }

    override fun partTwo() {
        val numSteps = getSimultaneousNumSteps(steps, network)
        println("Part 2: $numSteps")
    }

    fun parseNetwork(input: Array<String>): Map<String, Pair<String, String>> {
        val network = mutableMapOf<String, Pair<String, String>>()

        val regex = Regex("[A-Z0-9]+")
        for (step in input) {
            val matches = regex.findAll(step).toList()
            network[matches[0].value] = matches[1].value to matches[2].value
        }
        return network
    }

    fun getNumSteps(steps: String, network: Map<String, Pair<String, String>>): Int {
        var cur = "AAA"
        var numSteps = 0

        while (cur != "ZZZ") {
            val index = numSteps % steps.length
            val direction = steps[index]
            cur = move(cur, direction, network)
            numSteps++
        }

        return numSteps
    }

    private fun move(
        cur: String,
        direction: Char,
        network: Map<String, Pair<String, String>>
    ): String {
        var cur1 = cur
        cur1 = when (direction) {
            'R' -> network[cur1]!!.second
            'L' -> network[cur1]!!.first
            else -> cur1
        }
        return cur1
    }

    fun getStartingNodes(network: Map<String, Pair<String, String>>): List<String> {
        return network.keys.filter { it.endsWith('A') }
    }

    fun getSimultaneousNumSteps(steps: String, network: Map<String, Pair<String, String>>): Long {
        val nodes = getStartingNodes(network)

        val counts = nodes.map {
            var numSteps = 0
            var cur = it
            while (!cur.endsWith('Z')) {
                val index = numSteps % steps.length
                val direction = steps[index]
                cur = move(cur, direction, network)
                numSteps++
            }
            numSteps.toLong()
        }

        return counts.reduce { acc, i -> lcm(acc, i) }
    }

    private fun lcm(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }
}