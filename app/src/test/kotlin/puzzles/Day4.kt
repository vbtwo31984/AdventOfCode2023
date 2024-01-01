package puzzles

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class Day4Test {
    val puzzle = Day4()
    val input1 = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
    val input2 = "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"

    @Test
    fun testGetYourNumbers() {
        val expected = listOf(41, 48, 83, 86, 17)
        val actual = puzzle.getYourNumbers(input1)
        assertEquals(expected, actual)
    }

    @Test
    fun testGetWinningNumbers() {
        val expected = listOf(83, 86, 6, 31, 17, 9, 48, 53)
        val actual = puzzle.getWinningNumbers(input1)
        assertEquals(expected, actual)
    }

    @Test
    fun testGetNumberOfWinners() {
        assertEquals(puzzle.getNumberOfWinners(input1), 4)
        assertEquals(puzzle.getNumberOfWinners(input2), 0)
    }

    @Test
    fun testGetPoints() {
        assertEquals(puzzle.getPoints(input1), 8.0)
        assertEquals(puzzle.getPoints(input2), 0.0)
    }

    @Test
    fun testCreateCardMap() {
        val input = arrayOf("1", "2")
        val expected = mapOf(1 to 1, 2 to 1)
        assertEquals(puzzle.createCardMap(input), expected)
    }
}