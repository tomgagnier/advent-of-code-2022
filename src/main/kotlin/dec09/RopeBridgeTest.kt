package dec09

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RopeBridgeTest {
    val example13 = """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
        """.trimIndent()

    val input = aoc.text(this, "input")

    @Test fun `part 1`() {
        assertEquals(13, numberOfTailPositions(2, example13))
        assertEquals(5874, numberOfTailPositions(2, input))
    }

    val example36 = """
        R 5
        U 8
        L 8
        D 3
        R 17
        D 10
        L 25
        U 20
        """.trimIndent()

    @Test fun `part 2`() {
        assertEquals(36, numberOfTailPositions(10, example36))
        assertEquals(2467, numberOfTailPositions(10, input))
    }
}