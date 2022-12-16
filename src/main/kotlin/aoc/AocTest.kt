package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AocTest {
    @Test fun `is digits`() {
        assertTrue("01234".isDigits())
        assertFalse("01x234".isDigits())
    }

    @Test fun `aoc file`() {
        assertEquals("src/main/kotlin/aoc/input", file(this, "input").path)
        assertTrue(file(this, "input").exists())
    }

    @Test fun `aoc text`() {
        assertTrue(text(this, "input").startsWith("Kubla Khan"))
    }
}