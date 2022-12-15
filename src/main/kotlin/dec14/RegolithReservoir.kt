package dec14

data class P(val x: Int, val y: Int) {
    operator fun plus(that: P) = P(x + that.x, y + that.y)
    override fun toString(): String = "($x,$y)"
}

fun scan(text: String) = text.lines().asSequence().map { l ->
    l.split("->")
        .map { p -> p.trim().split(",") }
        .map { a -> P(a[0].toInt(), a[1].toInt()) }
        .zipWithNext()
}.flatten().map {
    if (it.first.x < it.second.x) (it.first.x..it.second.x).map { x -> P(x, it.first.y) }
    else if (it.first.x > it.second.x) (it.first.x downTo it.second.x).map { x -> P(x, it.first.y) }
    else if (it.first.y < it.second.y) (it.first.y..it.second.y).map { y -> P(it.first.x, y) }
    else (it.first.y downTo it.second.y).map { y -> P(it.first.x, y) }
}.flatten().toSet()

data class RegolithReservoir(val rocks: Set<P>, val floor: Int?) {
    val maxY = rocks.maxOf { it.y }

    companion object {
        fun withAbyss(text: String) = RegolithReservoir(scan(text), null)
        fun withFloor(text: String): RegolithReservoir = RegolithReservoir(scan(text), 2)
    }

    val source = P(500, 0)
    var occupied = rocks.toMutableSet()

    override fun toString() = (0..maxY).joinToString("\n") { y ->
        (occupied.minOf { it.x }..occupied.maxOf { it.x }).map { x -> P(x, y) }.joinToString("") { p ->
            if (p == source) "+" else if (p in rocks) "#" else if (p in occupied) "o" else "."
        }
    }

    fun releaseSand(): Boolean {
        var position = source
        var next = move(position)
        while (next != null) {
            if (floor == null && next.y > maxY) return false
            position = next
            next = move(position)
        }
        return occupied.add(position)
    }

    fun move(p: P): P? = listOf(P(0, 1), P(-1, 1), P(1, 1))
        .map { p + it }
        .find {
            it !in occupied &&
            if (floor == null)
                true //it.y in 0 until maxY + 1
            else
                it.y < floor + maxY
        }

    fun maxSand(): Int {
        while (releaseSand()) Unit
        return sandCount()
    }

    fun sandCount() = occupied.size - rocks.size
}
