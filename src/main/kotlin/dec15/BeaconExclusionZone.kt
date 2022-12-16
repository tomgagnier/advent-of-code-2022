package dec15

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class P(val x: Int, val y: Int) {
    fun distanceTo(p: P) = abs(x - p.x) + abs(y - p.y) // Manhattan Distance

    operator fun plus(that: P) = P(x + that.x, y + that.y)
    operator fun minus(that: P) = P(x - that.x, y - that.y)

    override fun toString(): String = "($x,$y)"

    fun perimeterOf(radius: Int) = buildSet {// Manhattan Perimeter
        addAll(setOf(P(x + radius, y), P(x, y + radius), P(x - radius, y), P(x, y - radius)))
        addAll((1 until radius).map { r -> P(x + r, y + radius - r) })
        addAll((1 until radius).map { r -> P(x - r, y + radius - r) })
        addAll((1 until radius).map { r -> P(x - r, r - y - radius) })
        addAll((1 until radius).map { r -> P(r + x, r - y - radius) })
    }
}

data class SensorReadings(val sensorReadings: List<Pair<P, P>>) {
    companion object {
        val regex = Regex("""Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)""")

        fun parse(sample1: String) = SensorReadings(
            sample1.lines()
                .mapNotNull { regex.matchEntire(it) }
                .map {
                    P(it.groups[1]?.value?.toInt()!!, it.groups[2]?.value?.toInt()!!) to
                    P(it.groups[3]?.value?.toInt()!!, it.groups[4]?.value?.toInt()!!)
                }
                .toList())
    }

    val sensors = sensorReadings.map { (sensor, beacon) -> sensor to sensor.distanceTo(beacon) }

    val minX = min(sensorReadings.minOf { it.first.x }, sensorReadings.minOf { it.second.x })
    val maxX = max(sensorReadings.maxOf { it.first.x }, sensorReadings.maxOf { it.second.x })

    fun beaconsOnRow(y: Int) =
        sensorReadings.filter { it.second.y == y }.map { it.second.x }.toSet()

    fun sensorCoverageOnRow(y: Int) = rangesOn(y)
        .map { it.toSet() }
        .reduce { s1, s2 -> s1.union(s2) }

    fun rangesOn(y: Int) = sensors
        .map { (sensor, radius) ->
            val dy = abs(y - sensor.y)
            val dx = radius - dy
            val r1 = sensor.x - dx
            val r2 = sensor.x + dx
            (r1..r2)
        }
        .filter { it.first <= it.last } // might not be needed
        .sortedWith { r1, r2 ->
            if (r1.first < r2.first) -1
            else if (r1.first > r2.first) 1
            else r1.last.compareTo(r2.last)
        }

    fun tuningFrequency(distressBeacon: P) = 4_000_000L * distressBeacon.x + distressBeacon.y

    fun part1(y: Int) = (sensorCoverageOnRow(y) - beaconsOnRow(y)).count()

    fun part2(dimension: Int): Long {
        val boundary = 0..dimension
        // Restrict search to points just outside sensor radius and within boundary
        val candidates = sensors.flatMap { (sensor, radius) ->
            sensor.perimeterOf(radius + 1).filter { p -> p.x in boundary && p.y in boundary }
        }
        val candidate = candidates.first { c ->
            sensors.none { (s, r) -> s.distanceTo(c) <= r }
        }
        return tuningFrequency(candidate)
    }
}
