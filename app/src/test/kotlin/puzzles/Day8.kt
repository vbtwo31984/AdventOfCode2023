package puzzles

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8Test {
    private val puzzle = Day8()
    private val input = """LLR

        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent().split("\n").toTypedArray()
    private val input2 = """LR

11A = (11B, XXX)
11B = (XXX, 11Z)
11Z = (11B, XXX)
22A = (22B, XXX)
22B = (22C, 22C)
22C = (22Z, 22Z)
22Z = (22B, 22B)
XXX = (XXX, XXX)""".trimIndent().split("\n").toTypedArray()

    @Test
    fun testParseNetwork() {
        val result = puzzle.parseNetwork(input.copyOfRange(2, input.size))
        val expected = mapOf(
            "AAA" to ("BBB" to "BBB"),
            "BBB" to ("AAA" to "ZZZ"),
            "ZZZ" to ("ZZZ" to "ZZZ")
        )
        assertEquals(result, expected)
    }

    @Test
    fun testGetNumSteps() {
        val network = mapOf(
            "AAA" to ("BBB" to "BBB"),
            "BBB" to ("AAA" to "ZZZ"),
            "ZZZ" to ("ZZZ" to "ZZZ")
        )
        val steps = input[0]
        val result = puzzle.getNumSteps(steps, network)
        assertEquals(result, 6)
    }

    @Test
    fun testGetStartingNodes() {
        val network = puzzle.parseNetwork(input2.copyOfRange(2, input2.size))
        val result = puzzle.getStartingNodes(network)
        val expected = listOf("11A", "22A")
        assertEquals(result, expected)
    }

    @Test
    fun testGetSimultaneousNumSteps() {
        val network = puzzle.parseNetwork(input2.copyOfRange(2, input2.size))
        val steps = input2[0]
        val result = puzzle.getSimultaneousNumSteps(steps, network)
        assertEquals(result, 6)
    }
}