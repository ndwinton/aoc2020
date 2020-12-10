package advent2020

fun joltageDifferences(adapterList: List<Int>): Pair<Int,Int> =
    (adapterList + 0)
        .sorted()
        .let { it + (it.last() + 3) }
        .zipWithNext()
        .map { it.second - it.first }
        .groupBy { it }
        .let { Pair(it[1]!!.size, it[3]!!.size) }

fun joltageCombinations(adapterList: List<Int>): Long {
    val sorted = adapterList.sorted().let { it + (it.last() + 3) }
    val waysToGetToAdapter = mutableMapOf<Int,Long>()

    waysToGetToAdapter[0] = 1
    sorted.forEach { adapter ->
        waysToGetToAdapter[adapter] = waysToGetToAdapter.getOrDefault(adapter - 1, 0) +
                waysToGetToAdapter.getOrDefault(adapter - 2, 0) +
                waysToGetToAdapter.getOrDefault(adapter - 3, 0)

    }
    return waysToGetToAdapter[sorted.last()]!!
}