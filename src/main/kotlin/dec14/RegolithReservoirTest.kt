package dec14

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RegolithReservoirTest {

    val sample = """
        498,4 -> 498,6 -> 496,6
        503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent()

    val input = aoc.text(this, "input")

    @Test fun `scan of sample`() {
        assertEquals(
            setOf(
                Point(498,4), Point(498,5), Point(498,6),
                Point(497,6), Point(496,6),
                Point(503,4), Point(502,4),
                Point(502,5), Point(502,6), Point(502,7), Point(502,8), Point(502,9),
                Point(501,9), Point(500,9), Point(499,9), Point(498,9), Point(497,9), Point(496,9), Point(495,9), Point(494,9))
            , scan(sample)
        )
    }

    @Test fun part1Sample() {
        assertEquals(24, RegolithReservoir.withAbyss(sample).maxSand())
    }

    @Test fun part1Input() {
        assertEquals(1078, RegolithReservoir.withAbyss(input).maxSand())
    }

    @Test fun part2Sample() {
        assertEquals(93, RegolithReservoir.withFloor(sample).maxSand())
    }

    @Test fun part2Input() {
        assertEquals(30157, RegolithReservoir.withFloor(input).maxSand())
    }

    @Test fun sand() {
        val sand = RegolithReservoir.withAbyss(sample).sand()
        while (sand.hasNext()) {
            println(sand.next())
        }
    }
}