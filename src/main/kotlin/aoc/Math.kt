package aoc

fun <T> combinationsOf(list: List<T>) = (0..list.size - 2)
    .flatMap { i -> (i + 1 until list.size).map { j -> list[i] to list[j] } }
