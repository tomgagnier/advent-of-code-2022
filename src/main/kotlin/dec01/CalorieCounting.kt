package dec01

fun sortedSum(it: String): List<Int> = it
    .split("\n\n")
    .map { paragraph -> paragraph.lines().sumOf { it.toInt() } }
    .sorted()

fun part1(input: String) = sortedSum(input).takeLast(1).sum()

fun part2(input: String) = sortedSum(input).takeLast(3).sum()
