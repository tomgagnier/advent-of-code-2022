package dec06

fun messageOffset(datastream: String, packetSize: Int) = datastream
    .windowedSequence(packetSize) { it.toSet().size == packetSize }.indexOf(true) + packetSize
