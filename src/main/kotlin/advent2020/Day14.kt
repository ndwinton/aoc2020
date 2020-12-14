package advent2020

class Mask(maskString: String) {
    val andMask: Long
    val orMask: Long

    init {
        val maskValues = maskString.fold(Pair(0L, 0L)) { pair, c ->
            Pair(
                (pair.first shl 1) or (if (c == '1') 1 else 0),
                (pair.second shl 1) or (if (c == '0') 0 else 1)
            )
        }

        orMask = maskValues.first
        andMask = maskValues.second
    }

    constructor() : this("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")

    override fun toString(): String {
        return "Mask(andMask=$andMask, orMask=$orMask)"
    }
}

tailrec fun parseAndApply(input: List<String>, mask: Mask = Mask(), state: Map<String,Long> = emptyMap()): Map<String,Long> {
    if (input.isEmpty()) return state

    val words = input.first().split(" ")
    return when {
        words[0] == "mask" -> parseAndApply(input.drop(1), Mask(words[2]), state)
        else -> parseAndApply(input.drop(1), mask, state + Pair(words[0], (words[2].toLong() and mask.andMask or mask.orMask)))
    }
}

fun day14Part1(input: List<String>): Long = parseAndApply(input).values.fold(0L) { acc, next -> acc + next }

class Mask2(maskString: String) {
    val orMask: Long
    val floatingMask: Long

    init {
        val maskValues = maskString.fold(Pair(0L, 0L)) { pair, c ->
            Pair(
                (pair.first shl 1) or (if (c == '1') 1 else 0),
                (pair.second shl 1) or (if (c == 'X') 1 else 0)
            )
        }

        orMask = maskValues.first
        floatingMask = maskValues.second
    }

    constructor() : this("000000000000000000000000000000000000")

    override fun toString(): String {
        return "Mask2(orMask=$orMask, anyMask=$floatingMask)"
    }
}

tailrec fun parseAndApply2(input: List<String>, mask: Mask2 = Mask2(), state: Map<Long,Long> = emptyMap()): Map<Long,Long> {
    if (input.isEmpty()) return state

    val words = input.first().split(" ")
    return when {
        words[0] == "mask" -> parseAndApply2(input.drop(1), Mask2(words[2]), state)
        else -> {
            val location = words[0].drop(4).dropLast(1).toLong()
            val value = words[2].toLong()
            val overwritten = valuesFromFloatingMask(mask.floatingMask)
                .map { it or (location and mask.floatingMask.inv()) or mask.orMask }
                .distinct()
                .map { Pair(it, value) }.toMap()
            parseAndApply2(input.drop(1), mask, state + overwritten)}
    }
}

fun valuesFromFloatingMask(value: Long): List<Long> {
    val powersOf2 = (0..35).map { value and (1L shl it) }.distinct()

    tailrec fun combinations(powersOf2: List<Long>, values: List<Long> = emptyList()): List<Long> =
        if (powersOf2.isEmpty()) values
        else combinations(powersOf2.drop(1), values + powersOf2.first() + values.map { it + powersOf2.first() })

    return combinations(powersOf2).distinct()
}

fun day14Part2(input: List<String>): Long { return parseAndApply2(input).values.fold(0L) { acc, next -> acc + next } }

