package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AocTest {
    @Test fun `is digits`() {
        assertTrue("01234".isDigits())
        assertFalse("01x234".isDigits())
    }

    @Test fun `combinations of`() {
        assertEquals(listOf(1 to 2, 1 to 3, 1 to 4, 2 to 3, 2 to 4, 3 to 4), combinationsOf(listOf(1, 2, 3, 4)))
    }
}