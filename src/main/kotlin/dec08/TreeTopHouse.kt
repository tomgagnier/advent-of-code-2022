package dec08

data class Forest(val trees: List<String>) {
    companion object {
        fun of(text: String): Forest = Forest(text.lines())
    }

    val maxI = trees.size
    val maxJ = trees[0].length
    val insideTrees = (1..maxI - 2).flatMap { i -> (1..maxJ - 2).map { j -> i to j } }

    fun at(tree: Pair<Int, Int>) = trees[tree.first][tree.second]

    fun lineOfSights(tree: Pair<Int, Int>) = listOf(
        (tree.first - 1 downTo 0).map { i -> i to tree.second },
        (tree.first + 1 until maxI).map { i -> i to tree.second },
        (tree.second - 1 downTo 0).map { j -> tree.first to j },
        (tree.second + 1 until maxJ).map { j -> tree.first to j }
    )

    fun invisible(): List<Pair<Int, Int>> = insideTrees.filter { insideTree ->
        lineOfSights(insideTree).all { blockingTrees ->
            blockingTrees.any { blockingTree ->
                at(blockingTree) >= at(insideTree)
            }
        }
    }

    fun treesVisibleFrom(tree: Pair<Int, Int>) = lineOfSights(tree)
        .map { los ->
            var hasNext = true
            los.takeWhile {
                val result = hasNext
                hasNext = at(tree) > at(it)
                result
            }
        }

    fun scenicScoreOf(tree: Pair<Int, Int>) = treesVisibleFrom(tree)
        .map { los -> los.size }.fold(1) { a, s -> a * s }

    fun maxScenicScore() = insideTrees.maxOf { scenicScoreOf(it) }

    fun numberOfVisibleTrees() = maxI * maxJ - invisible().count()
}
