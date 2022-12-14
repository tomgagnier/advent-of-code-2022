package dec13

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DistressSignalTest {
    val sample = """
        [1,1,3,1,1]
        [1,1,5,1,1]
        
        [[1],[2,3,4]]
        [[1],4]
        
        [9]
        [[8,7,6]]
        
        [[4,4],4,4]
        [[4,4],4,4,4]
        
        [7,7,7,7]
        [7,7,7]
        
        []
        [3]
        
        [[[]]]
        [[]]
        
        [1,[2,[3,[4,[5,6,7]]]],8,9]
        [1,[2,[3,[4,[5,6,0]]]],8,9]
        """.trimIndent()

    @Test fun parse() {
        assertEquals(
            ListPacket(
                listOf(
                    ListPacket(listOf(IntPacket(1))),
                    ListPacket(listOf(IntPacket(2), IntPacket(3), IntPacket(4)))
                )
            ),
            Packet.parse("[[1],[2,3,4]]")
        )
    }

    @Test fun compare() {
        assertTrue(Packet.parse("[[1],[2,3,4]]") == Packet.parse("[[1],[2,3,4]]"))
        assertTrue(Packet.parse("[[1],[2,3,4]]") < Packet.parse("[[1],[2,3,5]]"))
        assertTrue(Packet.parse("[1,[2,3,4]]") < Packet.parse("[[1],[2,3,3]]"))
        assertTrue(Packet.parse("[[1],[2,3,4]]") > Packet.parse("[[1],[2,3,3]]"))
    }

    @Test fun sample() {
        val sample = DistressSignal.parse(sample)
        assertEquals(13, sample.part1())
        assertEquals(140, sample.part2())
    }

    @Test fun input() {
        val input = DistressSignal.parse(aoc.text(this, "input"))
        assertEquals(5057, input.part1())
        assertEquals(28152, input.part2())
    }
}
