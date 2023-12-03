import puzzles.Day1
import puzzles.Puzzle

fun main(args: Array<String>) {
    val puzzle = getPuzzle(args[0])
    puzzle.partOne()
    puzzle.partTwo()
}

fun getPuzzle(day: String): Puzzle {
    if(day == "1") return Day1()
    throw IllegalArgumentException("Day $day not found")
}