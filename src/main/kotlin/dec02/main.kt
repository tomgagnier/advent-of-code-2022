package dec02

import java.io.File

fun main() {
    fun printScore(strategyGuide: String, scoring: Map<String, Int>) =
        println(File(strategyGuide).readLines().sumOf { match -> scoring[match]!! })

    fun printAnswers(path: String) {
        println(path)
        printScore(
            strategyGuide = path, scoring = mapOf(
                "B X" to 1, "C Y" to 2, "A Z" to 3,
                "A X" to 4, "B Y" to 5, "C Z" to 6,
                "C X" to 7, "A Y" to 8, "B Z" to 9,
            )
        )
        printScore(
            strategyGuide = path, scoring = mapOf(
                "B X" to 1, "C X" to 2, "A X" to 3,
                "A Y" to 4, "B Y" to 5, "C Y" to 6,
                "C Z" to 7, "A Z" to 8, "B Z" to 9
            )
        )
    }
    printAnswers("src/main/kotlin/dec02/example")
    printAnswers("src/main/kotlin/dec02/puzzle-input")
}
