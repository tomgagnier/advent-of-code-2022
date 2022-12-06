package dec02

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RockPaperScissorsTest {
    val example = aoc.lines(this, "example")
    val input = aoc.lines(this, "input")

    @Test fun `part 1`() {
        assertEquals(15, part1(example))
        assertEquals(11150, part1(input))
    }

    @Test fun `part 2`() {
        assertEquals(12, part2(example))
        assertEquals(8295, part2(input))
    }
}