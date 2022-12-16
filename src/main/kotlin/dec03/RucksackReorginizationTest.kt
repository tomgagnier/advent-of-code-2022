package dec03

import aoc.text
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RucksackReorginizationTest {
    val example = text(this, "example").lines()
    val input = text(this, "input").lines()

    @Test fun `priority of`() {
        assertEquals(1, priorityOf('a'))
        assertEquals(26, priorityOf('z'))
        assertEquals(27, priorityOf('A'))
        assertEquals(52, priorityOf('Z'))
    }

    @Test fun `shared item in`() {
        assertEquals('b', sharedItemIn(listOf("abc", "zbd", "bad")))
    }

    @Test fun `part 1`() {
        assertEquals(157, part1(example))
        assertEquals(7742, part1(input))
    }

    @Test fun `part 2`() {
        assertEquals(70, part2(example))
        assertEquals(2276, part2(input))
    }
}
