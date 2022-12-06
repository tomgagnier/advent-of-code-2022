package dec03

fun priorityOf(item: Char): Int = when {
    item >= 'a' -> item - 'a' + 1
    else -> item - 'A' + 27
}

fun sharedItemIn(rucksacks: List<String>): Char =
    rucksacks.map { items -> items.toSet() }.reduce { l, r -> l intersect r }.first()

fun part1(rucksacks: List<String>): Int = rucksacks
    .map { r -> listOf(r.substring(0, r.length / 2), r.substring(r.length / 2)) }
    .sumOf { l -> priorityOf(sharedItemIn(l)) }

fun part2(rucksacks: List<String>): Int = rucksacks
    .chunked(3)
    .sumOf { l -> priorityOf(sharedItemIn(l)) }
