package dec15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BeaconExclusionZoneTest {
    val sampleText = """
        Sensor at x=2, y=18: closest beacon is at x=-2, y=15
        Sensor at x=9, y=16: closest beacon is at x=10, y=16
        Sensor at x=13, y=2: closest beacon is at x=15, y=3
        Sensor at x=12, y=14: closest beacon is at x=10, y=16
        Sensor at x=10, y=20: closest beacon is at x=10, y=16
        Sensor at x=14, y=17: closest beacon is at x=10, y=16
        Sensor at x=8, y=7: closest beacon is at x=2, y=10
        Sensor at x=2, y=0: closest beacon is at x=2, y=10
        Sensor at x=0, y=11: closest beacon is at x=2, y=10
        Sensor at x=20, y=14: closest beacon is at x=25, y=17
        Sensor at x=17, y=20: closest beacon is at x=21, y=22
        Sensor at x=16, y=7: closest beacon is at x=15, y=3
        Sensor at x=14, y=3: closest beacon is at x=15, y=3
        Sensor at x=20, y=1: closest beacon is at x=15, y=3
        """.trimIndent()

    val sample = SensorReadings.parse(sampleText)
    val input = SensorReadings.parse(aoc.text(this, "input"))

    @Test fun `parse beacons`() {
        assertEquals(P(2, 18) to P(-2, 15), sample.sensorReadings[0])
        assertEquals(P(20, 1) to P(15, 3), sample.sensorReadings[13])
        assertEquals(14, sample.sensorReadings.size)
        assertEquals(-2, sample.minX)
        assertEquals(25, sample.maxX)
    }

    @Test fun `manhattan distance`() {
        assertEquals(0, P(0, 0).distanceTo(P(0, 0)))
        assertEquals(2, P(0, 0).distanceTo(P(2, 0)))
        assertEquals(2, P(0, 0).distanceTo(P(-2, 0)))
        assertEquals(3, P(0, 0).distanceTo(P(2, -1)))
    }

    @Test fun `perimeter of`() {
        assertEquals(setOf(P(0, 0)), P(0, 0).perimeterOf(0))
        assertEquals(setOf(P(1, 0), P(0, 1), P(-1, 0), P(0, -1)), P(0, 0).perimeterOf(1))
        assertEquals(
            setOf(P(2, 0), P(0, 2), P(-2, 0), P(0, -2), P(1, 1), P(-1, 1), P(-1, -1), P(1, -1)),
            P(0, 0).perimeterOf(2)
        )
        assertEquals(
            setOf(
                P(3, 0), P(2, 1), P(1, 2), P(0, 3), P(-1, 2), P(-2, 1),
                P(-3, 0), P(-2, -1), P(-1, -2), P(0, -3), P(1, -2), P(2, -1)
            ),
            P(0, 0).perimeterOf(3)
        )
    }

    @Test fun `part 1`() {
        assertEquals(26, sample.part1(10))
        assertEquals(4985193, input.part1(2_000_000))
    }

    @Test fun `part 2`() {
        assertEquals(56_000_011L, sample.part2(20))
        assertEquals(11583882601918L, input.part2(4_000_000))
    }
}