package aoc

inline fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val list = ArrayList<T>()
    for (item in this) {
        list.add(item)
        if (predicate(item))
            break
    }
    return list
}

operator fun Pair<Int, Int>.minus(p: Pair<Int, Int>) = first - p.first to second - p.second

fun String.isDigits() = all { it.isDigit() }

