package dec09

operator fun Pair<Int, Int>.minus(p: Pair<Int, Int>) = first - p.first to second - p.second

fun Pair<Int, Int>.follow(h: Pair<Int, Int>): Pair<Int, Int> {
    return when (h - this) {
        0 to 2 -> up()
        0 to -2 -> down()
        -2 to 0 -> left()
        2 to 0 -> right()
        2 to 2, 2 to 1, 1 to 2 -> right().up()
        -2 to 2, -2 to 1, -1 to 2 -> left().up()
        -2 to -2, -2 to -1, -1 to -2 -> left().down()
        2 to -2, 1 to -2, 2 to -1 -> right().down()
        else -> this
    }
}

fun Pair<Int, Int>.up() = this.first to this.second + 1
fun Pair<Int, Int>.down() = this.first to this.second - 1
fun Pair<Int, Int>.left() = this.first - 1 to this.second
fun Pair<Int, Int>.right() = this.first + 1 to this.second

data class RopeBridge(val numberOfKnots:Int) {
    val knots = MutableList(numberOfKnots) {0 to 0}
    val path = mutableSetOf<Pair<Int, Int>>()

    fun move(m: Pair<String, Int>) = repeat(m.second) { move(m.first) }

    fun move(direction: String) {
        when (direction) {
            "U" -> knots[0] = (knots[0]).up()
            "D" -> knots[0] = knots[0].down()
            "L" -> knots[0] = knots[0].left()
            "R" -> knots[0] = knots[0].right()
            else -> error("unknown direction $direction")
        }
        (1 until knots.size).forEach { knots[it] = knots[it].follow(knots[it - 1]) }
        path.add(knots.last())
    }
}

fun movesFrom(text: String) =
    text.lines().map { it.split(" ") }.map { it[0] to it[1].toInt() }

fun numberOfTailPositions(numberOfKnots: Int, input: String): Int {
    val rope = RopeBridge(numberOfKnots)
    movesFrom(input).forEach { rope.move(it) }
    return rope.path.count()
}