package dec08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TreeTopHouseTest {
    val example = Forest.of(
        """
            30373
            25512
            65332
            33549
            35390""".trimIndent()
    )
    val input = Forest.of(aoc.text(this, "input"))

    @Test fun `max i and j`() {
        assertEquals(5, example.maxI)
        assertEquals(5, example.maxJ)
    }

    @Test fun invisible() {
        assertEquals(listOf(1 to 3, 2 to 2, 3 to 1, 3 to 3), example.invisible())
    }

    @Test fun `trees visible from`() {
        assertEquals(
            listOf(listOf(0 to 2), listOf(2 to 2, 3 to 2), listOf(1 to 1), listOf(1 to 3, 1 to 4)),
            example.treesVisibleFrom(1 to 2)
        )
    }

    @Test fun `scenice score of`() {
        assertEquals(4, example.scenicScoreOf(1 to 2))
    }

    @Test fun `part 1`() {
        assertEquals(21, example.numberOfVisibleTrees())
        assertEquals(1672, input.numberOfVisibleTrees())
    }

    @Test fun `part 2`() {
        assertEquals(8, example.maxScenicScore())
        assertEquals(327180, input.maxScenicScore())
    }
}
