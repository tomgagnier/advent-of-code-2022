package aoc

data class Matrix<T>(val rows: List<List<T>>) {
    companion object {
        fun from(text: String) = Matrix(text.lines().map { it.toList() })
    }

    val maxI = rows[0].size - 1
    val maxJ = rows.size - 1
    val size = rows[0].size * rows.size
    val insides = (1 until maxI).flatMap { i -> (1 until maxJ).map { j -> i to j } }
    val edges =
        ((0 until maxI).map { it to 0 } + (0 until maxJ).map { maxI to it } +
        (maxI downTo 1).map { it to maxJ } + (maxJ downTo 1).map { 0 to it })

    operator fun get(i: Int, j: Int) = rows[j][i]
    operator fun get(ij: Pair<Int, Int>) = rows[ij.second][ij.first]

    fun viewFrom(i: Int, j: Int) = listOf(
        (i - 1 downTo 0).map { this[it, j] },
        (i + 1..maxI).map { this[it, j] },
        (j - 1 downTo 0).map { this[i, it] },
        (j + 1..maxJ).map { this[i, it] })

    fun viewFrom(p: Pair<Int, Int>) = viewFrom(p.first, p.second)

    override fun toString(): String =
        rows.joinToString("\n") { it.joinToString(" ") { t -> t.toString() } }
}