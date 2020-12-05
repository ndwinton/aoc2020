package advent2020

import java.io.File
import java.io.Reader

fun main(args: Array<String>) {
    when (args[0]) {
        "1" -> runDay01()
        "2" -> runDay02()
        "3" -> runDay03()
        "4" -> runDay04()
        "5" -> runDay05()
        else -> println("""
            https://adventofcode.com/2020/
            
            Usage: Main day-number
            
            Inputs are assumed to be in 'inputs/NN.txt' where 'NN' is the 2-digit day number.
            If the file isn't present, it reads from stdin instead.
            """.trimIndent())
    }
}

fun runDay01() {
    println("Part 1: " + day01(2, 2020, *inputAsIntList(1).toIntArray()))
    println("Part 2: " + day01(3, 2020, *inputAsIntList(1).toIntArray()))
}

fun runDay02() {
    println("Part 1: " + day02Part1(inputLines(2)))
    println("Part 2: " + day02Part2(inputLines(2)))
}

fun runDay03() {
    val map = Day03(inputLines(3))
    println("Part 1: " + map.countTrees(3, 1))
    val product = map.productForStrategies(listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2)))
    println("Part 2: $product")
}

fun runDay05() {
    println("Part 1: " + inputLines(5).map { decodePassId(it) }.maxOf { it })
    println("Part 2: " + inputLines(5).map { decodePassId(it) }.let { findMissing(it) })
}

fun runDay04() {
    println("Part 1: " + inputAsText(4).toRecordsWithMandatoryKeys().count())
    println("Part 2: " + inputAsText(4).countValidPassportRecords())
}
fun inputForDay(dayNum: Int): Reader = File(String.format("inputs/%02d.txt", dayNum))
    .let { if (it.exists()) it.bufferedReader() else System.`in`.bufferedReader() }

fun inputLines(dayNum: Int): List<String> = inputForDay(dayNum).readLines()

fun inputAsIntList(dayNum: Int) = inputLines(dayNum).map { it.toInt() }

fun inputAsText(dayNum: Int) = inputForDay(dayNum).readText()

