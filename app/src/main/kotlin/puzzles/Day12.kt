package puzzles

class Day12 : Puzzle(12) {
    var memo = mutableMapOf<Pair<String, String>, Int>()
    override fun partOne() {
        println(solveMemoized("?#?#?#?#?#?#?#?", "1,3,1,6"))
    }

    override fun partTwo() {
        TODO("Not yet implemented")
    }

    fun solveMemoized(record: String, check: String): Int {
        val key = record to check
        if (memo.containsKey(key)) {
            return memo[key]!!
        }
        memo[key] = solve(record, check)
        return memo[key]!!
    }

    fun solve(record: String, check: String): Int {
        if ((check.isEmpty() || check == "0") && record.isEmpty()) return 1
        if (check.isEmpty() && record.all { it == '.' || it == '?' }) return 1
        if (check == "0" && record.all { it == '.' || it == '?' }) return 1
        if (check[0] == '0' && record[0] == '#') return 0
        val subRecord = record.substring(1)
        if (check[0] == '0') return solveMemoized(subRecord, check.substring(2))

        val nums = check.split(',').map { it.toInt() }.toMutableList()
        nums[0]--
        val reducedNums = nums.joinToString(",")
        val rest = record.trimStart('.')
        if (rest.isEmpty()) return 0
        val subRest = rest.substring(1)
        if (subRest[0] == '#') return solveMemoized(subRest, reducedNums)
        return solveMemoized(subRest, check) + solveMemoized(subRest, reducedNums)
    }
}