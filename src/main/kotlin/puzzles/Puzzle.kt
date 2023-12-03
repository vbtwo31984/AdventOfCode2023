package puzzles

abstract class Puzzle {
    abstract val fileName: String
    protected val input: Array<String>
        get() = loadInput(fileName)

    abstract fun partOne()
    abstract fun partTwo()

    private fun loadInput(fileName: String): Array<String> {
        return this.javaClass.getResource(fileName)!!.readText().split("\n").toTypedArray()
    }
}