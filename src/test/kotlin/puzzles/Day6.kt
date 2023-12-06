package puzzles

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class Day6Test {
    val puzzle = Day6()
    @Test
    fun testFindDistance() {
        assertEquals(puzzle.findDistance(1, 7),6)
        assertEquals(puzzle.findDistance(2, 7), 10)
        assertEquals(puzzle.findDistance(3, 7), 12)
        assertEquals(puzzle.findDistance(4, 7), 12)
    }

    @Test
    fun testFindMin() {
        assertEquals(puzzle.findMin(7, 9), 2)
        assertEquals(puzzle.findMin(15, 40), 4)
        assertEquals(puzzle.findMin(30, 200), 11)
    }
}