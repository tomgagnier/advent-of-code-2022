package dec02

fun main() {
    fun printAnswers(path: String) {
        println(path)
        val guide = java.io.File(path).readLines()
        println(guide.sumOf { match ->
            mapOf(
                "B X" to 1, "C Y" to 2, "A Z" to 3,
                "A X" to 4, "B Y" to 5, "C Z" to 6,
                "C X" to 7, "A Y" to 8, "B Z" to 9,
            )[match]!!
        })
        println(guide.sumOf { map ->
            mapOf(
                "B X" to 1, "C X" to 2, "A X" to 3,
                "A Y" to 4, "B Y" to 5, "C Y" to 6,
                "C Z" to 7, "A Z" to 8, "B Z" to 9
            )[map]!!
        })
    }

    printAnswers("src/main/kotlin/dec02/example")
    printAnswers("src/main/kotlin/dec02/puzzle-input")
}
