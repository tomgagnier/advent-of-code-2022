package dec05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SupplyStacksTest {
    val example = aoc.file(this, "example").readText()
    val input = aoc.file(this, "input").readText()

    @Test fun `move from`() {
        assertEquals(
            listOf(
                Move(1, 1, 0),
                Move(3, 0, 2),
                Move(2, 1, 0),
                Move(1, 0, 1)
            ), Move.from(example)
        )
    }

    @Test fun `stack offsets`() {
        assertEquals(listOf(1, 5, 9), stackOffsets(example))
        assertEquals(listOf(1, 5, 9, 13, 17, 21, 25, 29, 33), stackOffsets(input))
    }

    @Test fun `new stack`() {
        assertEquals(listOf(listOf('N', 'Z'), listOf('D', 'C', 'M'), listOf('P')), newStack(example))
    }

    @Test fun `part 1`() {
        assertEquals("CMZ", part1(example))
        assertEquals("FJSRQCFTN", part1(input))
    }

    @Test fun `part 2`() {
        assertEquals("MCD", part2(example))
        assertEquals("CJVLJQPHS", part2(input))
    }
}