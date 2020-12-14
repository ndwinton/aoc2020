package advent2020

class Mask(maskString: String) {
    val andMask: Long
    val orMask: Long

    init {
        val maskValues = maskString.fold(Pair(0L, 0L)) { pair, c -> Pair(
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