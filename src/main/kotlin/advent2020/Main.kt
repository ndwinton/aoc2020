package advent2020

import java.io.File

fun main(args: Array<String>) {
    when (args[0]) {
        "1" -> runDay01()
        "2" -> runDay02()
        "3" -> runDay03()
        else -> println("""
            https://adventofcode.com/2020/
            
            Usage: Main day-number
            """.trimIndent())
    }
}

fun runDay01() {
    println("Part 1: " + day01(2, 2020, *fileToInts(inputName(1)).toIntArray()))
    println("Part 2: " + day01(3, 2020, *fileToInts(inputName(1)).toIntArray()))
}

fun runDay02() {
    println("Part 1: " + day02Part1(fileToLines(inputName(2))))
    println("Part 2: " + day02Part2(fileToLines(inputName(2))))
}

fun runDay03() {
    val map = Day03(fileToLines(inputName(3)))
    println("Part 1: " + map.countTrees(3, 1))
    val product = map.productForStrategies(listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2)))
    println("Part 2: $product")
}

fun inputName(num: Int) = String.format("inputs/%02d.txt", num)
fun fileToLines(filename: String) = File(filename).readLines()
fun fileToInts(filename: String) = File(filename).readLines().map { it.toInt() }

