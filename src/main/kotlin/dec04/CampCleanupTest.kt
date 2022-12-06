package dec04

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CampCleanupTest {
    val example = aoc.file(this, "example").readText()
    val input = aoc.file(this, "input").readText()

    @Test fun overlaps() {
        assertTrue((1..4).overlaps(3..4))
        assertTrue((1..4).overlaps(0..1))
        assertTrue((1..4).overlaps(2..3))

        assertFalse((1..4).overlaps(-1..0))
        assertFalse((1..4).overlaps(5..6))
    }

    @Test fun inside() {
        assertTrue((1..4).contains(1..4))
        assertTrue((1..4).contains(1..3))
        assertTrue((1..4).contains(2..4))
        assertTrue((1..4).contains(2..3))

        assertFalse((1..4).contains(-1..0))
        assertFalse((1..4).contains(-1..1))
        assertFalse((1..4).contains(4..6))
        assertFalse((1..4).contains(5..6))
    }

    @Test fun `part 1`() {
        assertEquals(2, part1(example))
        assertEquals(518, part1(input))
    }

    @Test fun `part 2`() {
        assertEquals(4, part2(example))
        assertEquals(909, part2(input))
    }
}
