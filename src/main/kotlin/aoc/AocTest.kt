package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AocTest {
    @Test fun `is digits`() {
        assertTrue("01234".isDigits())
        assertFalse("01x234".isDigits())
    }
}