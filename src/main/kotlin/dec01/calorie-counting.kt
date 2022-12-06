package dec01

import aoc.file

fun main() {
    fun sortedSum(it: String): List<Int> = file(object {}, it)
        .readText()
        .split("\n\n")
        .map { paragraph -> paragraph.lines().sumOf { it.toInt() } }
        .sorted()

    listOf("example", "puzzle-input").forEach {
        println(it)
        val sortedSums = sortedSum(it)
        println("\t${sortedSums.takeLast(1).sum()}")
        println("\t${sortedSums.takeLast(3).sum()}")
    }
}
