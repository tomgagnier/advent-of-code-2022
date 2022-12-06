package dec03

fun main() {
    fun sharedItemIn(rucksacks: List<String>) =
        rucksacks.map { items -> items.toSet() }.reduce { l, r -> l intersect r }.first()

    fun priorityOf(item: Char) = when {
        item >= 'a' -> item - 'a' + 1
        else -> item - 'A' + 27
    }

    listOf("example", "puzzle-input").forEach {
        println(it)
        val rucksacks = aoc.file(object {}, it).readLines()
        println(rucksacks
            .map { r -> listOf(r.substring(0, r.length / 2), r.substring(r.length / 2)) }
            .sumOf { l -> priorityOf(sharedItemIn(l)) })
        println(
            rucksacks
            .chunked(3)
            .sumOf { l -> priorityOf(sharedItemIn(l)) })
    }
}
