package dec07

fun String.cd(directory: String) = when (directory) {
    "/" -> "/"
    ".." -> removeSuffix("/").replaceAfterLast("/", "")
    else -> "$this$directory/"
}

fun String.paths(): List<String> = buildList {
    var path = this@paths
    while (path.isNotEmpty()) {
        this += path
        path = path.cd("..")
    }
}

fun mapOfPathToSize(lines: List<String>): Map<String, Long> = buildMap {
    var pwd = "/"
    lines.mapNotNull { Regex("""\$ cd (.*)|(\d+).*""").matchEntire(it) }.forEach {
        it.groups[1]?.value?.let { dir ->
            pwd = pwd.cd(dir)
        } ?: it.groups[2]?.value?.let { bytes ->
            pwd.paths().forEach { path -> put(path, getOrElse(path) { 0 } + bytes.toLong()) }
        }
    }
}

fun part1(lines: List<String>): Long =
    mapOfPathToSize(lines).values.filter { it < 100_000 }.sum()

fun part2(lines: List<String>): Long {
    val total = 70_000_000
    val required = 30_000_000
    val sizes = mapOfPathToSize(lines).values
    val used = sizes.max()
    val min = required - (total - used)
    return sizes.filter { it > min }.minOf { it }
}
