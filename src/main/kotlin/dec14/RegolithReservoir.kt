package dec14

data class Point(val x: Int, val y: Int) {
    operator fun plus(that: Point) = Point(x + that.x, y + that.y)
    override fun toString(): String = "($x,$y)"

    fun toCardinalRange(p2: Point) =
        if (y == p2.y) {
            if (x < p2.x) (x..p2.x).map { x -> Point(x, y) }
            else (x downTo p2.x).map { x -> Point(x, y) }
        } else if (x == p2.x) {
            if (y < p2.y) (y..p2.y).map { y -> Point(x, y) }
            else (y downTo p2.y).map { y -> Point(x, y) }
        } else {
            error("expecting horizontal or vertical line segment")
        }
}

fun scan(cave: String) = cave.lines().asSequence()
    .map { line ->
        line.split("->")
            .map { it.trim().split(",").map { n -> n.toInt() } }
            .map { Point(it[0], it[1]) }
            .zipWithNext()
    }
    .flatten()
    .map { it.first.toCardinalRange(it.second) }
    .flatten()
    .toSet()


data class RegolithReservoir(val rocks: Set<Point>, val floor: Int?) {
    val maxY = rocks.maxOf { it.y }

    companion object {
        fun withAbyss(text: String) = RegolithReservoir(scan(text), null)
        fun withFloor(text: String): RegolithReservoir = RegolithReservoir(scan(text), 2)
    }

    val source = Point(500, 0)

    fun inTheAbyss(p: Point) = floor == null && p.y == maxY
    fun onTheFloor(p: Point) = floor != null && p.y == maxY + floor - 1

    fun sand() = Sand()

    inner class Sand : Iterator<Point> {
        var sand = mutableListOf<Point>()
        var next: Point = source

        override fun next(): Point {
            sand.add(next)
            return next
        }

        override fun hasNext(): Boolean {
            next = source
            var peek = move(source)
            while (peek != null) {
                next = peek
                peek = move(peek)
            }
            val inTheAbyss = inTheAbyss(next)
            val onTheFloor = onTheFloor(next)
            val inSand = next in sand
            return !(inSand || inTheAbyss || onTheFloor)
        }

        fun move(p: Point): Point? = listOf(Point(0, 1), Point(-1, 1), Point(1, 1))
            .map { p + it }
            .find { it !in sand && !onTheFloor(p) && !inTheAbyss(p) }
    }


    var occupied = rocks.toMutableSet()

    override fun toString() = (0..maxY).joinToString("\n") { y ->
        (occupied.minOf { it.x }..occupied.maxOf { it.x }).map { x -> Point(x, y) }.joinToString("") { p ->
            if (p == source) "+" else if (p in rocks) "#" else if (p in occupied) "o" else "."
        }
    }

    fun releaseSand(): Boolean {
        var position = source
        var next = move(position)
        while (next != null) {
            position = next
            next = move(position)
        }
        return !inTheAbyss(position) && occupied.add(position)
    }

    fun move(p: Point): Point? = listOf(Point(0, 1), Point(-1, 1), Point(1, 1))
        .map { p + it }
        .find { it !in occupied && !onTheFloor(p) && !inTheAbyss(p) }

    fun maxSand(): Int {
        while (releaseSand()) Unit
        return sandCount()
    }

    fun sandCount() = occupied.size - rocks.size
}
