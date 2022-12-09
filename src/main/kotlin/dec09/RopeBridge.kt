package dec09

import kotlin.math.abs

typealias Knot = Pair<Int, Int>
typealias Move = Pair<String, Int>

operator fun Knot.minus(p: Knot) = first - p.first to second - p.second

fun Knot.up() = this.first to this.second + 1
fun Knot.down() = this.first to this.second - 1
fun Knot.left() = this.first - 1 to this.second
fun Knot.right() = this.first + 1 to this.second

fun Knot.follow(head: Knot): Knot {
    val delta = head - this
    return if (abs(delta.first) <= 1 && abs(delta.second) <= 1) this
    else if (delta.first == 0) if (delta.second < 0) down() else up()
    else if (delta.second == 0) if (delta.first < 0) left() else right()
    else if (delta.first < 0) if (delta.second < 0) left().down() else left().up()
    else if (delta.second < 0) right().down() else right().up()
}

data class RopeBridge(val numberOfKnots: Int) {
    val knots = MutableList(numberOfKnots) { 0 to 0 }
    val tailPositions = mutableSetOf(knots.last())

    fun move(m: Move) = repeat(m.second) { move(m.first) }

    fun move(direction: String) {
        when (direction) {
            "U" -> knots[0] = (knots[0]).up()
            "D" -> knots[0] = knots[0].down()
            "L" -> knots[0] = knots[0].left()
            "R" -> knots[0] = knots[0].right()
            else -> error("unknown direction $direction")
        }
        (1 until knots.size).forEach { knots[it] = knots[it].follow(knots[it - 1]) }
        tailPositions.add(knots.last())
    }
}

fun parseMoves(moves: String): List<Move> =
    moves.lines().map { it.split(" ") }.map { it[0] to it[1].toInt() }

fun numberOfTailPositions(numberOfKnots: Int, moves: String): Int {
    val rope = RopeBridge(numberOfKnots)
    parseMoves(moves).forEach { rope.move(it) }
    return rope.tailPositions.count()
}
