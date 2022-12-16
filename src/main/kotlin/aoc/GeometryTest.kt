package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.sqrt

typealias P = Point2

class GeometryTest {

    @Test fun `point operators`() {
        assertEquals(Point2(5, 3), Point2(3, -2) - Point2(-2, -5))
        assertEquals(Point2(3, -2), Point2(5, 3) + Point2(-2, -5))
    }

    @Test fun `to string`() {
        assertEquals("(3, 1)", Point2(3, 1).toString())
    }

    @Test fun `euclidean distance`() {
        assertEquals(sqrt(2.0), Point2(0, 0).euclideanDistanceTo(Point2(1, 1)))
    }

    @Test fun `chebyshev distance`() {
        assertEquals(4, Point2(1, 2).chebyshevDistanceTo(Point2(-3, 4)))
    }

    @Test fun `manhattan distance`() {
        P(5, 5).manhattanDistanceTo(P(0, 0))
    }

    @Test fun `compass direction`() {
        assert(CompassDirection.E.point + CompassDirection.W.point == P(0, 0))
        assert(CompassDirection.N.point + CompassDirection.S.point == P(0, 0))
        assert(CompassDirection.NE.point + CompassDirection.SW.point == P(0, 0))
        assert(CompassDirection.NW.point + CompassDirection.SE.point == P(0, 0))
    }

}