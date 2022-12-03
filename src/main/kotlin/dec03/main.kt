package dec03

fun main() {
    fun sharedItemIn(rucksacks: List<String>) =
        rucksacks.map { items -> items.toSet() }.reduce { l, r -> l intersect r }.first()

    fun priorityOf(item: Char) = when {
        item >= 'a' -> item - 'a' + 1
        else -> item - 'A' + 27
    }

    fun printAnswers(path: String) {
        println(path)
        val rucksacks = java.io.File(path).readLines()

        println(rucksacks
            .map { r -> listOf(r.substring(0, r.length / 2), r.substring(r.length / 2)) }
            .sumOf { l -> priorityOf(sharedItemIn(l)) })

        println(rucksacks
            .chunked(3)
            .sumOf { l -> priorityOf(sharedItemIn(l)) })
    }

    printAnswers("src/main/kotlin/dec03/example")
    printAnswers("src/main/kotlin/dec03/puzzle-input")
}
