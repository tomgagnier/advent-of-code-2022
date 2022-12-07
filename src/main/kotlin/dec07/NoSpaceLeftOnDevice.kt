package dec07

data class Node(val path: String, val size: Long, var children: MutableSet<String> = mutableSetOf()) {
    fun dir() = children.isNotEmpty()
    fun parentOf(node: Node) = node.path.startsWith(path)
}

typealias Nodes = MutableMap<String, Node>

fun toNodes(lines: MutableList<String>, path: String = "", nodes: Nodes = mutableMapOf()): Nodes {
    if (lines.isEmpty()) return nodes
    val line = lines.removeFirst()
    if (line.startsWith("$ cd ")) {
        return toNodes(
            lines, when (line.substring(5)) {
                "/" -> "/"
                ".." -> path.split("/").dropLast(2).joinToString("/") + "/"
                else -> "$path${line.substring(5)}/"
            }, nodes
        )
    }
    if (line.startsWith("$ ls")) {
        nodes[path] = Node(path, 0)
    } else if (line.startsWith("dir ")) {
        val fullName = "${path}${line.substring(4)}/"
        nodes[path]!!.children.add(fullName)
        nodes[fullName] = Node(fullName, 0)
    } else {
        val (size, name) = line.split(" ")
        val fullName = "$path$name"
        nodes[path]!!.children.add(fullName)
        nodes[fullName] = Node(fullName, size.toLong())
    }
    return toNodes(lines, path, nodes)
}

fun toSizes(nodes: Nodes) = nodes.filterValues { it.dir() }
    .map { kv -> nodes.filterValues { kv.value.parentOf(it) }.map { it.value.size }.sum() }

fun part1(lines: List<String>): Long =
    toSizes(toNodes(lines.toMutableList())).filter { it < 100_000 }.sum()

fun part2(lines: List<String>): Long {
    val diskSize = 70_000_000
    val required = 30_000_000
    val sizes = toSizes(toNodes(lines.toMutableList()))
    val min = required - (diskSize - sizes.max())
    return sizes.filter { it > min }.minOf { it }
}
