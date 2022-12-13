package dec12

import aoc.text
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

data class V(val x: Int = 0, val y: Int = 0) {
    fun neighbors(): Set<V> = setOf(copy(x = x - 1), copy(x = x + 1), copy(y = y - 1), copy(y = y + 1))

    override fun toString(): String = "($x,$y)"
}

data class HillClimbingAlgorithm(val elevations: Map<V, Int>, val start: V, val end: V) {

    companion object {
        fun parse(text: String): HillClimbingAlgorithm {
            val grid = text.lines().flatMapIndexed { y, r -> r.mapIndexed { x, c -> V(x, y) to c } }
            return HillClimbingAlgorithm(
                elevations = grid
                    .map { it.first to if (it.second == 'S') 'a' else if (it.second == 'E') 'z' else it.second }
                    .associate { it.first to it.second - 'a' },
                start = grid.find { it.second == 'S' }!!.first,
                end = grid.find { it.second == 'E' }!!.first
            )
        }
    }

    data class Path(val v: V, val steps: Int) : Comparable<Path> {
        override fun compareTo(other: Path): Int = this.steps.compareTo(other.steps)
    }

    fun shortestPath(begin: V, found: (V) -> Boolean, canStep: (Int, Int) -> Boolean): Int {
        val visited = mutableSetOf<V>()
        val paths = PriorityQueue<Path>().apply { add(Path(begin, 0)) }

        while (paths.isNotEmpty()) {
            val path = paths.poll()!!

            if (path.v !in visited) {
                visited += path.v
                val neighbors = path.v.neighbors().filter { it in elevations }
                    .filter { canStep(elevations.getValue(path.v), elevations.getValue(it)) }
                if (neighbors.any { found(it) }) return path.steps + 1
                paths.addAll(neighbors.map { Path(it, path.steps + 1) })
            }
        }
        throw IllegalStateException("unable to find end of $begin")
    }

    fun part1(): Int = shortestPath(begin = start, found = { it == end }, canStep = { from, to -> to - from <= 1 })

    fun part2(): Int =
        shortestPath(begin = end, found = { elevations[it] == 0 }, canStep = { from, to -> from - to <= 1 })
}

class Dec12Test {
    val sample = HillClimbingAlgorithm.parse(text(this, "sample"))
    val input = HillClimbingAlgorithm.parse(text(this, "input"))

    @Test fun `part 1`() {
        assertEquals(31, sample.part1())
        assertEquals(339, input.part1())
    }

    @Test fun `part 2`() {
        assertEquals(29, sample.part2())
        assertEquals(332, input.part2())
    }
}
