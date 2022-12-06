package dec04

fun ranges(input: String): List<Pair<IntRange, IntRange>> = input
    .split("\n")
    .map { it.split(Regex("[,-]")).map { s -> s.toInt() } }
    .map { Pair(IntRange(it[0], it[1]), IntRange(it[2], it[3])) }

fun IntRange.contains(that: IntRange) =
    contains(that.first) && contains(that.last)

fun IntRange.overlaps(that: IntRange) =
    (this intersect that).isNotEmpty()

fun part1(input: String): Int =
    ranges(input).count { it.first.contains(it.second) || it.second.contains(it.first) }

fun part2(input: String): Int =
    ranges(input).count { it.first.overlaps(it.second)}

