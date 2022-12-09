package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MatrixTest {
    val matrix = Matrix.from(
        """
        01234
        56789
        abcde
    """.trimIndent()
    )

    @Test fun constants() {
        assertEquals(4, matrix.maxI)
        assertEquals(2, matrix.maxJ)
        assertEquals(15, matrix.size)
        assertEquals(listOf(1 to 1, 2 to 1, 3 to 1), matrix.insides)
        assertEquals(
            listOf(
                0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 0, 4 to 1,
                4 to 2, 3 to 2, 2 to 2, 1 to 2, 0 to 2, 0 to 1
            ), matrix.edges
        )
    }

    @Test fun `index access`() {
        assertEquals('2', matrix[2, 0])
        assertEquals('2', matrix[2 to 0])
    }

    @Test fun `to string`() {
        assertEquals(
            """
            0 1 2 3 4
            5 6 7 8 9
            a b c d e""".trimIndent(),
            matrix.toString()
        )
    }

    @Test fun `view from`() {
        assertEquals(
            listOf(listOf(), listOf('1', '2', '3', '4'), listOf(), listOf('5', 'a')),
            matrix.viewFrom(0, 0)
        )
        assertEquals(
            listOf(listOf('a'), listOf('c', 'd', 'e'), listOf('6', '1'), listOf()),
            matrix.viewFrom(1, 2)
        )
        assertEquals(matrix.viewFrom(0 to 0), matrix.viewFrom(0, 0))
        assertEquals(matrix.viewFrom(1 to 2), matrix.viewFrom(1, 2))
    }
}