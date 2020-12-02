package advent2020

import java.io.File

fun main(args: Array<String>) {
    when (args[0]) {
        "1" -> println(day01(args[1].toInt(), 2020, *fileToInts(args[2]).toIntArray()))
        "2" -> println(File(args[1]).readLines().filter { it.toPwdRecord().valid }.count())
    }
}

fun fileToInts(filename: String) =
    File(filename).readLines().map { it.toInt() }
