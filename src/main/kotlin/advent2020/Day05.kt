package advent2020

// Basically this is decoding a 10-bit binary value

fun decodeRow(pass: String) =
        pass.take(7)
            .replace('B', '1')
            .replace('F', '0')
            .toInt(2)

fun decodeColumn(pass: String) =
    pass.drop(7)
        .replace('R', '1')
        .replace('L', '0')
        .toInt(2)

fun decodePassId(pass: String) = decodeRow(pass) * 8 + decodeColumn(pass)

// Trying to do this only using functional operations, no explicit loops
fun findMissing(list: List<Int>) =
    list.sorted()
        .mapIndexed { index, i -> Pair(index, i) }
        .let { map ->
            map.find { it.second - it.first != map.first().second - map.first().first }
        }!!.second - 1