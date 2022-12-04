package dec04

fun main() {
    fun inside(a: List<Int>) =
        a[0] <= a[2] && a[3] <= a[1] ||
        a[2] <= a[0] && a[1] <= a[3]

    fun duplicated(a: List<Int>) =
        a[0] <= a[2] && a[2] <= a[1] ||
        a[0] <= a[3] && a[2] <= a[1] ||
        a[2] <= a[0] && a[0] <= a[3] ||
        a[2] <= a[1] && a[1] <= a[3]

    listOf("example", "puzzle-input")
        .forEach { filename ->
            val input = java.io.File("src/main/kotlin/dec04/$filename")
                .readLines()
                .map { it.split(Regex("[,-]")).map { s -> s.toInt() } }
            println(filename)
            println("1) ${input.count { inside(it) }}")
            println("2) ${input.count { duplicated(it) }}")
        }
}
