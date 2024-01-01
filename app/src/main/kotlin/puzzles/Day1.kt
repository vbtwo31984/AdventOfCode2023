package puzzles

class Day1 : Puzzle(1) {
    override fun partOne() {
        val sum = input.sumOf { line -> getCalibrationValue(line) }
        println("Part 1: $sum")
    }

    private fun getCalibrationValue(line: String): Int {
        val digits = line.replace(Regex("[^0-9]"), "")
        return digits.first().digitToInt() * 10 + digits.last().digitToInt()
    }

    override fun partTwo() {
        val sum = input.sumOf { line -> getCalibrationValueWithReplacements(line) }
        println("Part 2: $sum")
    }

    private fun getCalibrationValueWithReplacements(line: String): Int {
        val replacedLine = replaceDigits(line)
        return getCalibrationValue(replacedLine)
    }

    private fun replaceDigits(line: String): String {
        val replacements = mapOf(
            "one" to "one1one",
            "two" to "two2two",
            "three" to "three3three",
            "four" to "four4four",
            "five" to "five5five",
            "six" to "six6six",
            "seven" to "seven7seven",
            "eight" to "eight8eight",
            "nine" to "nine9nine"
        )

        var mutableLine = line
        replacements.forEach { (key, value) ->
            mutableLine = mutableLine.replace(key, value)
        }
        return mutableLine
    }
}
