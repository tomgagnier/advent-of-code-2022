package aoc

import kotlin.math.abs
import kotlin.math.sqrt

data class Point2(val x: Int, val y: Int) {
    operator fun minus(p: Point2) = Point2(x - p.x, y - p.y)
    operator fun plus(p: Point2) = Point2(x + p.x, y + p.y)

    override fun toString(): String = "($x, $y)"

    fun chebyshevDistanceTo(that: Point2) = maxOf(abs(x - that.x), abs(y - that.y))

    fun euclideanDistanceTo(that: Point2) = sqrt((x - that.x) * (x - that.x).toDouble() + (y - that.y) * (y - that.y))

    fun manhattanDistanceTo(that: Point2) = abs(x - that.x) + abs(y - that.y)
}

enum class CompassDirection(x: Int, y: Int) {
    E(1, 0), NE(1, 1), N(0, 1), NW(-1, 1), W(-1, 0), SW(-1, -1), S(0, -1), SE(1, -1);

    val point = Point2(x, y)
}