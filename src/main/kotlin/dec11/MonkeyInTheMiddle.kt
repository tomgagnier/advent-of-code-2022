package dec11

data class Monkey(
    val items: ArrayDeque<Long>, val operation: String, val operand: String,
    val modulus: Long, val onZero: Int, val onNonZero: Int
) {
    val newWorry =
        if (operation == "+")
            if (operand == "old") { old: Long -> old + old }
            else { old: Long -> old + operand.toLong() }
        else
            if (operand == "old") { old: Long -> old * old }
            else { old: Long -> old * operand.toLong() }

    val throwTo = { w: Long -> if (w % modulus == 0L) onZero else onNonZero }

    var inspections: Long = 0

    override fun toString(): String = "$inspections $items"
}

data class MonkeyInTheMiddle(val filename: String) {
    val monkeys = aoc.text(this, filename).split("\n\n").map { p -> p.lines() }.map {

        val items = it[1].split(Regex("[ ,]+")).drop(3).map { s -> s.toLong() }
        val (operation, operand) = it[2].split(" ").takeLast(2)
        val modulus = it[3].split(" ").last().toLong()
        val onZero = it[4].split(" ").last().toInt()
        val nonZero = it[5].split(" ").last().toInt()

        Monkey(ArrayDeque(items), operation, operand, modulus, onZero, nonZero)
    }
    val leastCommonMultiple = monkeys.map { it.modulus }.reduce(Long::times)

    fun inspect(overflowGuard: (Long) -> Long) {
        monkeys.forEach { m ->
            while (m.items.isNotEmpty()) {
                m.inspections++
                val w = overflowGuard(m.newWorry(m.items.removeFirst()))
                monkeys[m.throwTo(w)].items += w
            }
        }
    }

    fun part1() = repeat(20) {
        inspect(overflowGuard = { l: Long -> l / 3L })
    }.let { monkeyBusiness() }

    fun part2() = repeat(10_000) {
        inspect(overflowGuard = { l: Long -> l % leastCommonMultiple })
    }.let { monkeyBusiness() }

    fun monkeyBusiness() = monkeys.map { it.inspections }.sorted().takeLast(2).reduce(Long::times)

    override fun toString(): String = monkeys.toString()
}
