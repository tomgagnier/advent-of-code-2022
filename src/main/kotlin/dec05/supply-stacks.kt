package dec05

data class Move(val amount: Int, val source: Int, val target: Int) {
    companion object {
        fun from(input: List<String>): List<Move> = input
            .map { Regex("""move (\d+) from (\d+) to (\d+)""").matchEntire(it) }
            .filterNotNull()
            .map {
                it.groupValues.takeLast(3).map { it.toInt() }.let { m -> Move(m[0], m[1] - 1, m[2] - 1) }
            }
    }
}

typealias Stacks = List<ArrayDeque<Char>>

fun newStack(input: List<String>): Stacks {
    val stackOffsets = input.last().foldIndexed(listOf<Int>()) { index, offsets, char ->
        when (char) {
            ' ' -> offsets
            else -> offsets + index
        }
    }
    return input.dropLast(1)
        .fold(stackOffsets.map { ArrayDeque() }) { stacks, line ->
            stackOffsets.forEachIndexed { index, offset ->
                if (offset < line.length && line[offset] != ' ') stacks[index] += line[offset]
            }
            stacks
        }
}

fun Stacks.part1(moves: List<Move>): Stacks = moves.forEach { move ->
    repeat(move.amount) { this[move.target].add(0, this[move.source].removeFirst()) }
}.let { this }

fun Stacks.part2(moves: List<Move>) = moves.forEach { move ->
    this[move.target].addAll(0, this[move.source].take(move.amount))
    repeat(move.amount) { this[move.source].removeFirst() }
}.let { this }

fun Stacks.top(): String = this.map { s -> s.first() }.joinToString("")

fun main() {
    listOf("example", "puzzle-input").forEach { name ->
        val (section1, section2) = aoc.file(object {}, name)
            .readText()
            .split("\n\n")
            .map { p -> p.split("\n") }

        println(name)
        println("\t1) ${newStack(section1).part1(Move.from(section2)).top()}")
        println("\t2) ${newStack(section1).part2(Move.from(section2)).top()}")
    }
}
