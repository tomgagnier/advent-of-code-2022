package dec01

fun main() {
    fun printAnswers(path: String) {
        val sortedSums = java.io.File(path)
            .readText()
            .split("\n\n")
            .map { paragraph -> paragraph.lines().sumOf { it.toInt() } }
            .sorted()
        println(path)
        println(sortedSums.takeLast(1).sum())
        println(sortedSums.takeLast(3).sum())
    }

    printAnswers("src/main/kotlin/dec01/example")
    printAnswers("src/main/kotlin/dec01/puzzle-input")
}
