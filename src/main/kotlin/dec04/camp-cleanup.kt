package dec04

fun IntRange.overlaps(that: IntRange) =
    this.first <= that.first && that.first <= this.last ||
    this.first <= that.last && that.first <= this.last

fun IntRange.inside(that: IntRange) =
    that.first <= this.first && this.last <= that.last

fun main() {
    listOf("example", "puzzle-input").forEach { name ->
        val ranges = aoc.file(object{}, name).readLines()
            .map { it.split(Regex("[,-]")).map { s -> s.toInt() } }
            .map { Pair(IntRange(it[0], it[1]), IntRange(it[2], it[3])) }
        println(name)
        println("1) ${ranges.count { it.first.inside(it.second) || it.second.inside(it.first) }}")
        println("2) ${ranges.count { it.first.overlaps(it.second) }}")
    }
}
