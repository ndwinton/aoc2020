package advent2020

fun day01(howMany: Int, target: Int, vararg numbers: Int): List<Int> =
    candidateLists(howMany, target, emptyList(), numbers.toList())
        .filter { it.size == howMany }
        .map { it.fold(1) { product, number -> product * number } }
        .distinct()

private fun candidateLists(howMany: Int, target: Int, current: List<Int>, numbers: List<Int>): List<List<Int>> =
    if (howMany == 1) listOf(numbers.filter { it == target } + current)
    else numbers.flatMapIndexed { index, number ->
        candidateLists(howMany - 1, target - number, current + number, numbers.take(index) + numbers.drop(index + 1))
    }
