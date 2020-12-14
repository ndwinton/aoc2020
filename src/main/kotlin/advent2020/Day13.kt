package advent2020

import java.math.BigInteger

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

fun findAlignmentTime(list: List<BusRecord>): BigInteger = chineseRemainder(list.map {
        val modulus = it.period.toBigInteger()
        val remainder = (modulus - it.offset.toBigInteger()) % modulus
        Pair(modulus, remainder)
    })

fun chineseRemainder(pairs: List<Pair<BigInteger, BigInteger>>): BigInteger {
    val product = pairs.fold(BigInteger.ONE) { acc, pair -> acc * pair.first }
    val sum = pairs.fold(BigInteger.ZERO) { acc, pair ->
        val p = product / pair.first
        acc + pair.second * p.modInverse(pair.first) * p
    }
    return sum % product
}

fun day13Part2(lines: List<String>) = findAlignmentTime(parseBusRecords(lines[1]))