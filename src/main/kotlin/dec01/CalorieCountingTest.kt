package dec01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalorieCountingTest {
    val example = aoc.text(this, "example")
    val input = aoc.text(this, "input")

    @Test fun `part 1`() {
        assertEquals(24000, part1(example))
        assertEquals(71023, part1(input))
    }

    @Test fun `part 2`() {
        assertEquals(45000, part2(example))
        assertEquals(206289, part2(input))
    }
}
