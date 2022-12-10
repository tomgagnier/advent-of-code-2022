package dec09

import kotlin.math.abs

data class V2(val x: Int, val y: Int) {
    operator fun minus(p: V2) = V2(x - p.x, y - p.y)
    operator fun plus(p: V2) = V2(x + p.x, y + p.y)

    override fun toString(): String = "($x, $y)"

    fun distance() = maxOf(abs(x), abs(y))
}

enum class Direction(x: Int, y: Int) {
    U(0, 1), D(0, -1), L(-1, 0), R(1, 0);

    val offset = V2(x, y)
}

data class Move(val direction: Direction, val count: Int)

fun offset(offset: V2): V2 =
    if (offset.distance() <= 1) V2(0, 0)
    else V2(offset.x.coerceIn(-1, 1), offset.y.coerceIn(-1, 1))

data class RopeBridge(val numberOfKnots: Int) {
    val knots = MutableList(numberOfKnots) { V2(0, 0) }
    val tailPositions = mutableSetOf(knots.last())

    fun move(m: Move) = repeat(m.count) {
        knots[0] = knots[0] + m.direction.offset
        knots.indices.zipWithNext {i, j -> knots[j] += offset(knots[i] - knots[j]) }
        tailPositions += knots.last()
    }
}

fun parseMoves(moves: String): List<Move> =
    moves.lines().map { it.split(" ") }.map { Move(Direction.valueOf(it[0]), it[1].toInt()) }

fun numberOfTailPositions(numberOfKnots: Int, moves: String): Int {
    val rope = RopeBridge(numberOfKnots)
    parseMoves(moves).forEach { rope.move(it) }
    return rope.tailPositions.count()
}
