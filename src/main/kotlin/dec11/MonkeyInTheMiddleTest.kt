package dec11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MonkeyInTheMiddleTest {
    @Test fun constructor() {
        val sample = MonkeyInTheMiddle("sample")
        assertEquals(listOf(79L, 98L), sample.monkeys[0].items)
        assertEquals(2, sample.monkeys[0].throwTo(23))
        assertEquals(3, sample.monkeys[0].throwTo(24))
        assertEquals(38, sample.monkeys[0].newWorry(2))
        assertEquals(4, sample.monkeys[2].newWorry(2))
        assertEquals(96577, sample.leastCommonMultiple)
    }

    @Test fun `part 1`() {
        assertEquals(10605, MonkeyInTheMiddle("sample").part1())
        assertEquals(100345, MonkeyInTheMiddle("input").part1())
    }

    @Test fun `part 2`() {
        assertEquals(2713310158, MonkeyInTheMiddle("sample").part2())
        assertEquals(28537348205, MonkeyInTheMiddle("input").part2())
    }
}