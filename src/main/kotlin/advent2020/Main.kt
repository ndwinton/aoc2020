package advent2020

import java.io.File

fun main(args: Array<String>) {
    when (args[0]) {
        "1" -> println(day01(*fileToInts(args[1]).toIntArray()))
    }
}

fun fileToInts(filename: String) =
    File(filename).readLines().map { it.toInt() }
