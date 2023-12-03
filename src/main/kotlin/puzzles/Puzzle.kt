package puzzles

abstract class Puzzle {
    private var input: Array<String> = arrayOf()
        get() = field

    abstract fun partOne()
    abstract fun partTwo()

    fun loadInput(fileName: String) {
        input = this.javaClass.getResource(fileName)!!.readText().split("\n").toTypedArray()
    }
}