package dec02

import java.io.File

fun main() {
    fun printAnswers(path: String) {
        println(path)
        val lines = File(path).readLines()
        println(lines.sumOf { line ->
            mapOf(
                "B X" to 1, "A X" to 4, "C X" to 7,
                "C Y" to 2, "B Y" to 5, "A Y" to 8,
                "A Z" to 3, "C Z" to 6, "B Z" to 9,
            )[line]!!
        })
        println(lines.sumOf { line ->
            mapOf(
                "A X" to 3, "B X" to 1, "C X" to 2,
                "A Y" to 4, "B Y" to 5, "C Y" to 6,
                "A Z" to 8, "B Z" to 9, "C Z" to 7
            )[line]!!
        })
    }

    printAnswers("src/main/kotlin/dec02/example")
    printAnswers("src/main/kotlin/dec02/puzzle-input")
}
