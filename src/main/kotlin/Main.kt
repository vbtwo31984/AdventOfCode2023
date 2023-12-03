import puzzles.*

fun main(args: Array<String>) {
    val puzzle = getPuzzle(args[0])
    puzzle.partOne()
    puzzle.partTwo()
}

fun getPuzzle(day: String): Puzzle {
    if (day == "1") return Day1()
    if (day == "2") return Day2()
    if(day == "3") return Day3()
//    if(day == "4") return Day4()
//    if(day == "5") return Day5()
//    if(day == "6") return Day6()
//    if(day == "7") return Day7()
//    if(day == "8") return Day8()
//    if(day == "9") return Day9()
//    if(day == "10") return Day10()
//    if(day == "11") return Day11()
//    if(day == "12") return Day12()
//    if(day == "13") return Day13()
//    if(day == "14") return Day14()
//    if(day == "15") return Day15()
//    if(day == "16") return Day16()
//    if(day == "17") return Day17()
//    if(day == "18") return Day18()
//    if(day == "19") return Day19()
//    if(day == "20") return Day20()
//    if(day == "21") return Day21()
//    if(day == "22") return Day22()
//    if(day == "23") return Day23()
//    if(day == "24") return Day24()
//    if(day == "25") return Day25()
    throw IllegalArgumentException("Day $day not found")
}