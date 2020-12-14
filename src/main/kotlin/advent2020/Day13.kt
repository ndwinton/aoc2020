package advent2020

fun parseBusIds(input: String) = input.split(",").filter { it != "x" }.map { it.toInt() }

fun earliestAfterTimestampForBusId(timestamp: Int, busId: Int) =
    generateSequence(0) { it + busId }
        .dropWhile { it <= timestamp }
        .first()

fun findShortestDelayAndId(timestamp: Int, idList: List<Int>): Pair<Int,Int> =
    idList.map { Pair(earliestAfterTimestampForBusId(timestamp, it) - timestamp, it) }.minByOrNull { it.first }!!

fun day13Part1(lines: List<String>) = findShortestDelayAndId(lines[0].toInt(), parseBusIds(lines[1])).let { it.first * it.second }

// For part 2 the key was knowing about the Chinese Remainder Theorem
// I needed that hint from the Reddit thread ...

data class BusRecord(val period: Long, val offset: Long)

fun parseBusRecords(input: String) =
    input.split(",")
        .mapIndexed { index, str -> if (str == "x") BusRecord(-1L, -1) else BusRecord(str.toLong(), index.toLong()) }
        .filter { it.period != -1L }

fun findAlignmentTime(list: List<BusRecord>): Long {

    val sorted = list.sortedByDescending { it.period }
    val modulus = sorted.first().period
    val remainder = ((modulus - sorted.first().offset) % modulus)

    return solve(modulus, remainder, sorted.drop(1))
}

private tailrec fun solve(step: Long, start: Long, list: List<BusRecord>): Long {
    println("$step / $start / $list")
    if (list.isEmpty()) return start

    val modulus = list.first().period
    val remainder = ((modulus - list.first().offset) % modulus)

    val found = generateSequence(start) { it + step }
        .dropWhile { (it % modulus) != remainder }
        .first()

    return solve(step * modulus, found, list.drop(1))
}

fun day13Part2(lines: List<String>) = findAlignmentTime(parseBusRecords(lines[1]))