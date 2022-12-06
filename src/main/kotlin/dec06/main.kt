package dec06

fun startOfPackatMarker(datastream: String, distict: Int): Int = (0 until datastream.length - distict)
    .find { datastream.subSequence(it, it + distict).toSet().size == distict }!! + distict

fun main() {
    listOf(4, 14).forEach {
        println(startOfPackatMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb", it))
        println(startOfPackatMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", it))
        println(startOfPackatMarker("nppdvjthqldpwncqszvftbrmjlhg", it))
        println(startOfPackatMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", it))
        println(startOfPackatMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", it))
        println(startOfPackatMarker(aoc.file(object {}, "puzzle-input").readText(), it))
    }
}
