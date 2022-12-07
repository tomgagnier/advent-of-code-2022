package dec07

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NoSpaceLeftOnDeviceTest {
    val example = aoc.lines(this, "example")
    val input = aoc.lines(this, "input")

    @Test fun part1() {
        assertEquals(95437, part1(example))
        assertEquals(1206825, part1(input))
    }

    @Test fun part2() {
        assertEquals(24933642, part2(example))
        assertEquals(9608311, part2(input))
    }
}