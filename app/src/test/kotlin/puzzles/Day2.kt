package puzzles

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day2Test {
    val puzzle = Day2()
    val contents = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    @Test
    fun testIsPossible() {
        assertTrue(
            puzzle.isPossible(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                contents
            )
        )
        assertTrue(
            puzzle.isPossible(
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                contents
            )
        )
        assertTrue(
            puzzle.isPossible(
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
                contents
            )
        )

        assertFalse(
            puzzle.isPossible(
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                contents
            )
        )
        assertFalse(
            puzzle.isPossible(
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                contents
            )
        )
    }

    @Test
    fun testGetDraws() {
        val input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
        val output = listOf(
            mapOf("blue" to 3, "red" to 4),
            mapOf("red" to 1, "green" to 2, "blue" to 6),
            mapOf("green" to 2)
        )

        assertEquals(output, puzzle.getDraws(input))
    }

    @Test
    fun testGetDrawMap() {
        val input = "3 blue, 4 red, 2 green"
        val output = mapOf("blue" to 3, "red" to 4, "green" to 2)

        assertEquals(output, puzzle.getDrawMap(input))
    }

    @Test
    fun testGetId() {
        assertEquals(puzzle.getId("Game 1: 1 red"), 1)
        assertEquals(puzzle.getId("Game 143: 1 blue"), 143)
    }


    @Test
    fun testGetMinimum() {
        val input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
        val output = mapOf(
            "red" to 4,
            "green" to 2,
            "blue" to 6
        )
        assertEquals(puzzle.getMinimum(input), output)
    }

    @Test
    fun testGetPower() {
        val input = mapOf(
            "red" to 4,
            "green" to 2,
            "blue" to 6
        )
        assertEquals(puzzle.getPower(input), 4 * 2 * 6)
    }
}