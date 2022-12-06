package dec06

import aoc.file
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TuningTroubleTest {
    private val input = file(object {}, "input").readText()

    @Test fun part1() {
        val packetSize = 4
        assertEquals(7, messageOffset("mjqjpqmgbljsphdztnvjfqwrcgsmlb", packetSize))
        assertEquals(5, messageOffset("bvwbjplbgvbhsrlpgdmjqwftvncz", packetSize))
        assertEquals(6, messageOffset("nppdvjthqldpwncqszvftbrmjlhg", packetSize))
        assertEquals(10, messageOffset("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", packetSize))
        assertEquals(11, messageOffset("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", packetSize))
        assertEquals(1175, messageOffset(input, packetSize))
    }

    @Test fun part2() {
        val packetSize = 14
        assertEquals(19, messageOffset("mjqjpqmgbljsphdztnvjfqwrcgsmlb", packetSize))
        assertEquals(23, messageOffset("bvwbjplbgvbhsrlpgdmjqwftvncz", packetSize))
        assertEquals(23, messageOffset("nppdvjthqldpwncqszvftbrmjlhg", packetSize))
        assertEquals(29, messageOffset("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", packetSize))
        assertEquals(26, messageOffset("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", packetSize))
        assertEquals(3217, messageOffset(input, packetSize))
    }
}