package aoc

fun file(anchor: Any, name: String) =
    java.io.File("src/main/kotlin/${anchor.javaClass.`package`.name}/$name")