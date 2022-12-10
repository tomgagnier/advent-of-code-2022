package dec10

import kotlin.math.absoluteValue

class CathodeRayTube(input: String) {
    val signals: List<Int> = buildList {
        this.add(1)
        input.lines().forEach { line ->
            this.add(0)
            if (line.startsWith("addx")) this.add(line.substringAfter(" ").toInt())
        }
    }.runningReduce(Int::plus)

    fun signalStrength(cycle: Int) = cycle * signals[cycle - 1]

    override fun toString(): String = signals
        .mapIndexed { pixel, signal -> (signal - (pixel % 40)).absoluteValue <= 1 }
        .windowed(40, 40, false)
        .joinToString("\n") { it.map { pixel -> if (pixel) '#' else ' ' }.joinToString("") }
}

fun CathodeRayTube.part1(): Int = (20..signals.size step 40).sumOf { cycle -> signalStrength(cycle) }

fun main() {
    val sample = CathodeRayTube(aoc.text(object{}, "sample"))
    println(sample.part1())
    println(sample)

    val input = CathodeRayTube(aoc.text(object{}, "input"))
    println(input.part1())
    println(input)
}
