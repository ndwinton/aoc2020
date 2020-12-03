package advent2020

import java.io.File

fun main(args: Array<String>) {
    when (args[0]) {
        "1" -> runDay01(args)
        "2" -> runDay02(args)
        "3" -> runDay03(args)
        else -> println("""
            https://adventofcode.com/2020/
            
            Usage: day-number args ...
        
            The arguments for the different days are as follows:
        
            1 how-many-in-sum input-file
            2 version input-file (where version is 1 or 2)
            3 input-file
            """.trimIndent())
    }
}

fun runDay01(args: Array<String>) = println(day01(args[1].toInt(), 2020, *fileToInts(args[2]).toIntArray()))

fun runDay02(args: Array<String>) = when (args[1]) {
    "1" -> println(day02Part1(fileToLines(args[2])))
    "2" -> println(day02Part2(fileToLines(args[2])))
    else -> println("Huh?")
}

fun runDay03(args: Array<String>) = println(Day03(fileToLines(args[1])).countTrees(3, 1))
fun fileToLines(filename: String) = File(filename).readLines()
fun fileToInts(filename: String) = File(filename).readLines().map { it.toInt() }

