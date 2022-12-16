package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ExtensionTest {
    @Test fun `is digit`() {
        assertTrue("01".isDigits())
        assertFalse("0a1".isDigits())
    }

    @Test fun `take until`() {
        assertEquals(listOf(1, 2, 3), listOf(1, 2, 3, 1).takeUntil { it >= 3 })
    }

    @Test fun `pair of int`() {
        assertEquals(-7 to 6, (1 to 3) - (8 to - 3))
    }
}