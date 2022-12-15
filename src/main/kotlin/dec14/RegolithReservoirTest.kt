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
        println(scan(sample))
        assertEquals(
            setOf(
                P(498,4), P(498,5), P(498,6),
                P(497,6), P(496,6),
                P(503,4), P(502,4),
                P(502,5), P(502,6), P(502,7), P(502,8), P(502,9),
                P(501,9), P(500,9), P(499,9), P(498,9), P(497,9), P(496,9), P(495,9), P(494,9))
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
}