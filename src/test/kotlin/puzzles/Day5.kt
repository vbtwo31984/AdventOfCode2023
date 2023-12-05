package puzzles

import org.testng.Assert.*
import org.testng.annotations.Test

class Day5Test {
    val mapping = Mapping("180784667 145454582 160895405")
    @Test
    fun testMappingInRange() {
        assertTrue(mapping.inRange(145454582))
        assertTrue(mapping.inRange(145454582 + 160895405))
        assertFalse(mapping.inRange(145454582 + 160895406))
    }

    @Test
    fun testMappingGetDestination() {
        assertEquals(mapping.getDestination(145454582), 180784667)
        assertEquals(mapping.getDestination(145454582 + 1000), 180784667 + 1000)
    }
}