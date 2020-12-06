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

fun highestPassIdInInput(lines: List<String>) = lines.map { decodePassId(it) }.maxOf { it }

// Trying to do this only using functional operations, no explicit loops

fun findMissing(list: List<Int>) =
    list.sorted()
        .zipWithNext()
        .find { it.second - it.first > 1 }!!.second - 1

fun missingPassIdInInput(lines: List<String>) = findMissing(lines.map { decodePassId(it) })