package dec01

import java.io.File

fun groupByParagraphs(filePath: String): List<List<String>> {
    val initial = mutableListOf(mutableListOf<String>())
    return File(filePath)
        .readLines()
        .fold(initial) { paragraphs, line ->
            if (line.isEmpty()) paragraphs.add(mutableListOf())
            else paragraphs.last().add(line)
            paragraphs
        }
}

private fun sortedCalorieSums(path: String): List<Int> {
    return groupByParagraphs(path)
        .map { paragraph -> paragraph.sumOf { it.toInt() } }
        .sorted()
}

private fun printAnswers(path: String) {
    val sums = sortedCalorieSums(path)
    println()
    println(path)
    println(sums.takeLast(1).sum())
    println(sums.takeLast(3).sum())
}

fun main() {
    printAnswers("src/main/kotlin/dec01/example")
    printAnswers("src/main/kotlin/dec01/puzzle-input")
}
