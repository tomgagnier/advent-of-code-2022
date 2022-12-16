package aoc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MathTest {
    @Test fun `combinations of`() {
        Assertions.assertEquals(
            listOf(1 to 2, 1 to 3, 1 to 4, 2 to 3, 2 to 4, 3 to 4),
            combinationsOf(listOf(1, 2, 3, 4))
        )
    }
}