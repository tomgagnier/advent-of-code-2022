package dec06

fun messageOffset(datastream: String, packetSize: Int) = datastream
    .windowedSequence(packetSize)
    .indexOfFirst { it.toSet().size == packetSize } + packetSize
