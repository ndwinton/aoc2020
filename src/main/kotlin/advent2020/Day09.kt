package advent2020

tailrec fun anyTwoSumToValue(value: Long, list: List<Long>): Boolean =
    when {
        list.isEmpty() -> false
        list.drop(1).any { it + list.first() == value } -> true
        else -> anyTwoSumToValue(value, list.drop(1))
    }

tailrec fun findFirstInvalidAfterPreamble(preambleSize: Int, list: List<Long>): Long =
    when {
        anyTwoSumToValue(list[preambleSize], list.take(preambleSize)) ->
            findFirstInvalidAfterPreamble(preambleSize, list.drop(1))
        else -> list[preambleSize]
    }

tailrec fun contiguousSumForValue(target: Long, list: List<Long>, length: Int = 1): List<Long> {
    val subList = list.take(length)
    val total = subList.sum()
    return when {
        subList.isEmpty() -> subList
        total == target -> subList
        total > target -> contiguousSumForValue(target, list.drop(1), 1)
        else -> contiguousSumForValue(target, list, length + 1)
    }
}

fun encryptionWeakness(preambleSize: Int, list: List<Long>) =
    contiguousSumForValue(findFirstInvalidAfterPreamble(preambleSize, list), list).let {
        it.minOf { it } + it.maxOf { it }
    }
