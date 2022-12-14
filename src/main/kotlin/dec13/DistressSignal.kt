package dec13

data class DistressSignal(val packets: List<Packet>) {
    companion object {
        fun parse(text: String): DistressSignal =
            DistressSignal(text.lines().filter { it.isNotEmpty() }.map { Packet.parse(it) })
    }

    fun part1() = packets
        .chunked(2)
        .mapIndexed { i, p -> if (p[0] < p[1]) i + 1 else 0 }
        .sum()

    val dividerPackets = listOf(Packet.parse("[[2]]"), Packet.parse("[[6]]"))

    fun part2() = (packets + dividerPackets)
        .sorted()
        .mapIndexed { i, p -> if (p in dividerPackets) i + 1 else 1 }
        .reduce(Int::times)
}

sealed interface Packet : Comparable<Packet> {
    companion object {
        fun parse(packet: String): Packet = parse(packet.toCharArray().filterNot { it == ',' }.iterator())

        fun parse(tokens: Iterator<Char>): Packet {
            val packets = mutableListOf<Packet>()
            while (tokens.hasNext()) {
                when (val token = tokens.next()) {
                    ']' -> return ListPacket(packets)
                    '[' -> packets.add(parse(tokens))
                    else -> packets.add(IntPacket(token.digitToInt()))
                }
            }
            return ListPacket(packets)
        }
    }
}

data class IntPacket(val int: Int) : Packet {
    fun toList(): Packet = ListPacket(listOf(this))

    override fun compareTo(other: Packet): Int =
        when (other) {
            is IntPacket -> int.compareTo(other.int)
            is ListPacket -> toList().compareTo(other)
        }

    override fun toString(): String = "$int"
}

data class ListPacket(val children: List<Packet>) : Packet {
    override fun compareTo(other: Packet): Int =
        when (other) {
            is IntPacket -> compareTo(other.toList())
            is ListPacket -> children
                .zip(other.children)
                .map { it.first.compareTo(it.second) }
                .firstOrNull { it != 0 } ?: children.size.compareTo(other.children.size)
        }

    override fun toString(): String = "$children"
}
