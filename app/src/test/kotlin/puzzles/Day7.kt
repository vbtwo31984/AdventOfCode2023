package puzzles

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Day7Test {
    val puzzle = Day7()

    @Test
    fun testIsFiveOfAKind() {
        assertTrue(puzzle.isFiveOfAKind("AAAAA"))
        assertFalse(puzzle.isFiveOfAKind("AAAAB"))
    }

    @Test
    fun testIsFourOfAKind() {
        assertTrue(puzzle.isFourOfAKind("AAAAC"))
        assertTrue(puzzle.isFourOfAKind("AAABA"))
        assertTrue(puzzle.isFourOfAKind("BAAAA"))
        assertFalse(puzzle.isFourOfAKind("BBAAA"))
    }

    @Test
    fun testIsFullHouse() {
        assertTrue(puzzle.isFullHouse("AABBB"))
        assertTrue(puzzle.isFullHouse("ABABA"))
        assertTrue(puzzle.isFullHouse("AAABB"))
        assertFalse(puzzle.isFullHouse("AAABC"))
    }

    @Test
    fun testIsThreeOfAKind() {
        assertTrue(puzzle.isThreeOfAKind("AAABC"))
        assertTrue(puzzle.isThreeOfAKind("ABACA"))
        assertTrue(puzzle.isThreeOfAKind("ACBBB"))
        assertFalse(puzzle.isThreeOfAKind("AABBC"))
    }

    @Test
    fun testCompareByChars() {
        assertTrue(puzzle.compareByChars("A2222", "K2222") < 0)
        assertTrue(puzzle.compareByChars("22222", "K2222") > 0)
        assertTrue(puzzle.compareByChars("22223", "22222") < 0)
    }
}