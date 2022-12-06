package dec02

fun part1(strategyGuide: List<String>) = strategyGuide.sumOf { match ->
    mapOf(
        "B X" to 1, "C Y" to 2, "A Z" to 3,
        "A X" to 4, "B Y" to 5, "C Z" to 6,
        "C X" to 7, "A Y" to 8, "B Z" to 9
    )[match]!!
}

fun part2(strategyGuide: List<String>) = strategyGuide.sumOf { match ->
    mapOf(
        "B X" to 1, "C X" to 2, "A X" to 3,
        "A Y" to 4, "B Y" to 5, "C Y" to 6,
        "C Z" to 7, "A Z" to 8, "B Z" to 9
    )[match]!!
}
