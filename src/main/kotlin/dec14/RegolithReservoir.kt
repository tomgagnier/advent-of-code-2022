package dec14

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

data class P(val x: Int, val y: Int) {
    operator fun plus(that: P) = P(x + that.x, y + that.y)
    override fun toString(): String = "($x,$y)"
}

data class RegolithReservoir(val rocks: Set<P>, val floor: Int?) {
    val source = P(500, 0)
    var occupied = rocks.toMutableSet()
    val maxY = occupied.maxOf { it.y }
    val moves = listOf(P(0, 1), P(-1, 1), P(1, 1))

    companion object {
        fun withAbyss(text: String) = RegolithReservoir(rocks(text), null)
        fun withFloor(text: String): RegolithReservoir = RegolithReservoir(rocks(text), 2)

        fun rocks(text: String) = text.lines().asSequence().map { l ->
            l.split("->")
                .map { p -> p.trim().split(",") }
                .map { a -> P(a[0].toInt(), a[1].toInt()) }.zipWithNext()
        }.flatten().map {
            if (it.first.x < it.second.x) (it.first.x..it.second.x).map { x -> P(x, it.first.y) }
            else if (it.first.x > it.second.x) (it.second.x..it.first.x).map { x -> P(x, it.first.y) }
            else if (it.first.y < it.second.y) (it.first.y..it.second.y).map { y -> P(it.first.x, y) }
            else (it.second.y..it.first.y).map { y -> P(it.first.x, y) }
        }.flatten().toSet()
    }

    override fun toString() = (0..maxY).joinToString("\n") { y ->
        (occupied.minOf { it.x }..occupied.maxOf { it.x }).map { x -> P(x, y) }.joinToString("") { p ->
            if (p == source) "+" else if (p in rocks) "#" else if (p in occupied) "o" else "."
        }
    }

    fun releaseSand(): Boolean {
        var position = source
        var next = move(position)
        while (next != null) {
            if (floor == null) {
                if (next.y > maxY) return false
            }
            position = next
            next = move(position)
        }
        return occupied.add(position)
    }

    fun move(p: P): P? = moves.map { p + it }.find { it !in occupied && (floor == null || it.y < floor + maxY) }

    fun maxSand(): Int {
        while (releaseSand()) Unit
        return sandCount()
    }

    fun sandCount() = occupied.size - rocks.size
}

class RegolithReservoirTest {

    val sample = """
        498,4 -> 498,6 -> 496,6
        503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent()

    val input = aoc.text(this, "input")

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
