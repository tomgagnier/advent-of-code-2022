package aoc

/** Assumes file is a resource in the same package as the anchor */
fun file(anchor: Any, name: String) =
    java.io.File("src/main/kotlin/${anchor.javaClass.`package`.name}/$name")

fun text(anchor: Any, name: String) =
    file(anchor, name).readText()
