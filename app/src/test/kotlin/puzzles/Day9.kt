package puzzles

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day9Test {
    val puzzle = Day9()

    @Test
    fun testGetNextInSequence() {
        assertEquals(puzzle.getNextInSequence(listOf(2, 2, 2)), 2)
        assertEquals(puzzle.getNextInSequence(listOf(0, 2, 4, 6)), 8)
        assertEquals(puzzle.getNextInSequence(listOf(10, 13, 16, 21, 30, 45)), 68)
    }

    @Test
    fun testGetPreviousInSequence() {
        assertEquals(puzzle.getPreviousInSequence(listOf(2, 2, 2)), 2)
        assertEquals(puzzle.getPreviousInSequence(listOf(0, 2, 4, 6)), -2)
        assertEquals(puzzle.getPreviousInSequence(listOf(10, 13, 16, 21, 30, 45)), 5)
    }
}