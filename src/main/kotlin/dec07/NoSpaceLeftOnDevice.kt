package dec07

data class Node(val path: String, val size: Long, val children: MutableSet<String> = mutableSetOf()) {
    fun isDirectory() = children.isNotEmpty()
    fun isParentOf(node: Node) = node.path.startsWith(path)
}

fun String.cd(dir: String) =
    when (dir) {
        "/" -> "/"
        ".." -> split("/").dropLast(2).joinToString("/") + "/"
        else -> "$this$dir/"
    }

fun toNodes(lines: List<String>, path: String = "", nodes: Map<String, Node> = mapOf()): Map<String, Node> {
    if (lines.isEmpty()) return nodes
    val line = lines.first()
    val rest = lines.drop(1)

    if (line.startsWith("$ cd "))
        return toNodes(rest, path.cd(line.split(" ")[2]), nodes)

    if (line.startsWith("$ ls"))
        return toNodes(rest, path, nodes + (path to Node(path, 0)))

    val node =
        if (line.startsWith("dir ")) {
            val name = line.split(" ")[1]
            Node("${path}$name/", 0)
        } else {
            val (size, name) = line.split(" ")
            Node("$path$name", size.toLong())
        }

    nodes[path]!!.children.add(node.path)
    return toNodes(rest, path, nodes + (node.path to node))
}

fun Map<String, Node>.toSizes() = this
    .filterValues { it.isDirectory() }
    .map { kv ->
        this.filterValues { kv.value.isParentOf(it) }
            .map { it.value.size }
            .sum()
    }

fun part1(lines: List<String>): Long =
    toNodes(lines).toSizes().filter { it < 100_000 }.sum()

fun part2(lines: List<String>): Long {
    val diskSize = 70_000_000
    val required = 30_000_000
    val sizes = toNodes(lines).toSizes()
    val min = required - (diskSize - sizes.max())
    return sizes.filter { it > min }.minOf { it }
}
