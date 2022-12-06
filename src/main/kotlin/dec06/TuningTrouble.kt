package dec06

fun messageOffset(datastream: String, packetSize: Int): Int = (0 until datastream.length - packetSize)
    .find { datastream.subSequence(it, it + packetSize).toSet().size == packetSize }!! + packetSize
