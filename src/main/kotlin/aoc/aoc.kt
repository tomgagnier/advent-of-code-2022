package aoc

/** Assumes file is a resource in the same package as the anchor */
fun file(anchor: Any, name: String) =
    java.io.File("src/main/kotlin/${anchor.javaClass.`package`.name}/$name")

fun text(anchor: Any, name: String) =
    file(anchor, name).readText()

fun lines(anchor: Any, name: String) =
    file(anchor, name).readLines()

inline fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val list = ArrayList<T>()
    for (item in this) {
        list.add(item)
        if (predicate(item))
            break
    }
    return list
}

fun Iterable<Int>.product(): Int =
    reduce { a, b -> a * b }

operator fun Pair<Int, Int>.minus(p: Pair<Int, Int>) = first - p.first to second - p.second

fun String.isDigits() = all { it.isDigit() }

fun <T> combinationsOf(list: List<T>) = (0..list.size - 2)
    .flatMap { i -> (i + 1 until list.size).map { j -> list[i] to list[j] } }
