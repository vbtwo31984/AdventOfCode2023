package puzzles

abstract class Puzzle(day: Int) {
    private val fileName: String
    protected val input: Array<String>
        get() = loadInput(fileName)

    init {
        fileName = "day$day.txt"
    }

    abstract fun partOne()
    abstract fun partTwo()

    private fun loadInput(fileName: String): Array<String> {
        return this.javaClass.getResource(fileName)!!.readText().split("\n").toTypedArray()
    }
}