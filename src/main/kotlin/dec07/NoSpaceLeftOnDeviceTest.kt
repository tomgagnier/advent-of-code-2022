package dec07

import aoc.text
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NoSpaceLeftOnDeviceTest {
    val example = text(this, "example").lines()
    val input = text(this, "input").lines()

    @Test fun cd() {
        assertEquals("/a/", "/a/b/".cd(".."))
        assertEquals("/", "/a".cd(".."))
        assertEquals("", "/".cd(".."))
    }

    @Test fun paths() {
        assertEquals(listOf("/a/b/c", "/a/b/", "/a/", "/"), "/a/b/c".paths())
    }

    @Test fun part1() {
        assertEquals(95437, part1(example))
        assertEquals(1206825, part1(input))
    }

    @Test fun part2() {
        assertEquals(24933642, part2(example))
        assertEquals(9608311, part2(input))
    }
}
