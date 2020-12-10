package advent2020

fun joltageDifferences(adapterList: List<Int>): Pair<Int,Int> =
    (adapterList + 0)
        .sorted()
        .let { it + (it.last() + 3) }
        .zipWithNext()
        .map { it.second - it.first }
        .groupBy { it }
        .let { Pair(it[1]!!.size, it[3]!!.size) }

fun adapterCombinations(adapterList: List<Int>): Long {
    val sortedList = adapterList.sorted().let { it + (it.last() + 3) }
    return pathsToAdapter(sortedList, mapOf(0 to 1))
}

tailrec fun pathsToAdapter(sortedList: List<Int>, knownPaths: Map<Int,Long>): Long {
    val nextAdapter = sortedList.first()
    val pathsToNext = knownPaths.getOrDefault(nextAdapter - 1, 0) +
            knownPaths.getOrDefault(nextAdapter - 2, 0) +
            knownPaths.getOrDefault(nextAdapter - 3, 0)
    return if (nextAdapter == sortedList.last()) pathsToNext
    else pathsToAdapter(sortedList.drop(1), knownPaths + Pair(nextAdapter, pathsToNext))
}
