package dec05

data class Move(val amount: Int, val source: Int, val target: Int) {
    companion object {
        fun from(input: String): List<Move> = input
            .split("\n")
            .filter { it.startsWith("move") }
            .map { it.split(" ").filterIndexed { i, _ -> i % 2 == 1 }.map { char -> char.toInt() } }
            .map { Move(it[0], it[1] - 1, it[2] - 1) }
    }
}

fun stackOffsets(input: String): List<Int> = input
    .split("\n")
    .find { it.matches(Regex("[ 1-9]+")) }!!
    .foldIndexed(listOf()) { index, offsets, char ->
        when (char) {
            ' ' -> offsets
            else -> offsets + index
        }
    }

fun newStack(input: String): List<ArrayDeque<Char>> = input
    .split("\n")
    .filter { it.contains('[') }
    .fold(stackOffsets(input).map { ArrayDeque() }) { stacks, line ->
        stackOffsets(input).forEachIndexed { index, offset ->
            if (offset < line.length && line[offset] != ' ') stacks[index] += line[offset]
        }
        stacks
    }

fun List<ArrayDeque<Char>>.top(): String =
    this.map { s -> s.first() }.joinToString("")

fun part1(input: String): String =
    Move.from(input).fold(newStack(input)) { stacks, move ->
        repeat(move.amount) { stacks[move.target].add(0, stacks[move.source].removeFirst()) }
        stacks
    }.top()

fun part2(input: String): String =
    Move.from(input).fold(newStack(input)) { stacks, move ->
        stacks[move.target].addAll(0, stacks[move.source].take(move.amount))
        repeat(move.amount) { stacks[move.source].removeFirst() }
        stacks
    }.top()
