package puzzles

class Day7 : Puzzle(7) {
    override fun partOne() {
        val sum = sortedGames().mapIndexed { index, s ->
            getScore(s) * (index + 1)
        }.sum()

        println("Part 1: $sum")
    }

    override fun partTwo() {
        val sum = sortedGamesWithJoker().mapIndexed { index, s ->
            getScore(s) * (index + 1)
        }.sum()

        println("Part 2: $sum")
    }

    fun isFiveOfAKind(input: String): Boolean {
        val char = input[0]
        for (i in 1..4) {
            if (input[i] != char) {
                return false
            }
        }
        return true
    }

    private fun sortedGames(): Array<String> {
        val game = input
        game.sortWith { a, b ->
            val gameA = getHand(a)
            val gameB = getHand(b)
            val aRank = getRank(gameA)
            val bRank = getRank(gameB)
            if (aRank != bRank) {
                aRank - bRank
            } else {
                compareByChars(gameA, gameB)
            }
        }
        return game
    }

    private fun sortedGamesWithJoker(): Array<String> {
        val game = input
        game.sortWith { a, b ->
            val gameA = getHand(a)
            val gameB = getHand(b)
            val aRank = getRankWithJoker(gameA)
            val bRank = getRankWithJoker(gameB)
            if (aRank != bRank) {
                aRank - bRank
            } else {
                compareByCharsWithJoker(gameA, gameB)
            }
        }
        return game
    }

    fun isFourOfAKind(input: String): Boolean {
        val map = createMap(input)
        val max = map.values.max()
        return max == 4
    }

    fun isFullHouse(input: String): Boolean {
        val map = createMap(input)
        val max = map.values.max()
        return max == 3 && map.size == 2
    }

    fun isThreeOfAKind(input: String): Boolean {
        val map = createMap(input)
        val max = map.values.max()
        return max == 3 && map.size == 3
    }

    private fun isTwoPair(input: String): Boolean {
        val map = createMap(input)
        val max = map.values.max()
        return max == 2 && map.size == 3
    }

    private fun isOnePair(input: String): Boolean {
        val map = createMap(input)
        val max = map.values.max()
        return max == 2 && map.size == 4
    }

    private fun getRank(input: String): Int {
        if (isFiveOfAKind(input)) {
            return 10
        }
        if (isFourOfAKind(input)) {
            return 9
        }
        if (isFullHouse(input)) {
            return 8
        }
        if (isThreeOfAKind(input)) {
            return 7
        }
        if (isTwoPair(input)) {
            return 6
        }
        if (isOnePair(input)) {
            return 5
        }
        return 4
    }

    private fun getRankWithJoker(input: String): Int {
        val possibilities = "23456789TQKA"
        if(input.contains('J')) {
            return possibilities.toCharArray().maxOf {
                getRank(input.replace('J', it))
            }
        }
        return getRank(input)
    }

    fun compareByChars(a: String, b: String): Int {
        val order = "23456789TJQKA"
        return compareByCharsWithOrder(a, b, order)
    }

    private fun compareByCharsWithJoker(a: String, b: String): Int {
        val order = "J23456789TQKA"
        return compareByCharsWithOrder(a, b, order)
    }

    private fun compareByCharsWithOrder(a: String, b: String, order: String): Int {
        for (i in 0..4) {
            val charA = a[i]
            val charB = b[i]
            val posA = order.indexOf(charA)
            val posB = order.indexOf(charB)
            if (posA != posB) {
                return posA - posB
            }
        }
        return 0
    }

    private fun getHand(input: String) = input.split(" ")[0]

    private fun getScore(input: String) = input.split(" ")[1].toLong()

    private fun createMap(input: String): MutableMap<Char, Int> {
        val map = mutableMapOf<Char, Int>()
        input.toCharArray().forEach {
            map[it] = (map[it] ?: 0) + 1
        }
        return map
    }
}