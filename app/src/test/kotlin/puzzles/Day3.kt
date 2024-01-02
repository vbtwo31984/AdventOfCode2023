package puzzles

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day3Test {
    val puzzle = Day3()
    val input = ("467..114..\n" +
            "...*......\n" +
            "..35..633.\n" +
            "......#...\n" +
            "617*......\n" +
            ".....+.58.\n" +
            "..592.....\n" +
            "......755.\n" +
            "...\$.*....\n" +
            ".664.598..").split("\n").toTypedArray()

    @Test
    fun testGetNumberWithCoord() {
        val input = "..35..633."
        val output = listOf(35 to 2, 633 to 6)
        assertEquals(puzzle.getNumberWithCoord(input).toList(), output)
    }

    @Test
    fun testIsPart() {
        assertTrue(puzzle.isPart('@'))
        assertTrue(puzzle.isPart('#'))
        assertFalse(puzzle.isPart('1'))
        assertFalse(puzzle.isPart('.'))
    }

    @Test
    fun testIsPartNumber() {
        assertTrue(puzzle.isPartNumber(467, 0, 0, input))
        assertFalse(puzzle.isPartNumber(114, 0, 5, input))
    }

    @Test
    fun testGetPossibleCoords() {
        val result = puzzle.getPossibleCoords(100, 5, 5)
        val expected = listOf(
            4 to 4, 4 to 5, 4 to 6, 4 to 7, 4 to 8,
            5 to 4, 5 to 8,
            6 to 4, 6 to 5, 6 to 6, 6 to 7, 6 to 8
        )
        assertEquals(result, expected)
    }

    @Test
    fun testIsPartCoord() {
        assertTrue(puzzle.isPart(1 to 3, input))
        assertTrue(puzzle.isPart(4 to 3, input))
        assertFalse(puzzle.isPart(-1 to -1, input))
        assertFalse(puzzle.isPart(0 to 0, input))
    }

    @Test
    fun testGetPossibleGears() {
        val output = puzzle.getPossibleGears(467, 0, 0, input)
        val expected = listOf(1 to 3)
        assertEquals(output, expected)
    }
}