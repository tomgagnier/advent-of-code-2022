package dec06

fun messageOffset(datastream: String, packetSize: Int): Int = (0 until datastream.length - packetSize)
    .find { datastream.subSequence(it, it + packetSize).toSet().size == packetSize }!! + packetSize

fun main() {
    listOf(4, 14).forEach { packetSize ->
        println(messageOffset("mjqjpqmgbljsphdztnvjfqwrcgsmlb", packetSize))
        println(messageOffset("bvwbjplbgvbhsrlpgdmjqwftvncz", packetSize))
        println(messageOffset("nppdvjthqldpwncqszvftbrmjlhg", packetSize))
        println(messageOffset("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", packetSize))
        println(messageOffset("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", packetSize))
        println(messageOffset(aoc.file(object {}, "puzzle-input").readText(), packetSize))
    }
}
