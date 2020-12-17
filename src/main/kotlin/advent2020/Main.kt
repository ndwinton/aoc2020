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
        "6" -> runDay06()
        "7" -> runDay07()
        "8" -> runDay08()
        "9" -> runDay09()
        "10" -> runDay10()
        "11" -> runDay11()
        "12" -> runDay12()
        "13" -> runDay13()
        "14" -> runDay14()
        "15" -> runDay15()
        "16" -> runDay16()
        "17" -> runDay17()
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

fun runDay04() {
    println("Part 1: " + inputAsText(4).toRecordsWithMandatoryKeys().count())
    println("Part 2: " + inputAsText(4).countValidPassportRecords())
}

fun runDay05() {
    println("Part 1: " + highestPassIdInInput(inputLines(5)))
    println("Part 2: " + missingPassIdInInput(inputLines(5)))
}

fun runDay06() {
    println("Part 1: " + totalDistinctForGroups(inputAsText(6)))
    println("Part 1: " + totalCommonForGroups(inputAsText(6)))
}

fun runDay07() {
    println("Part 1: " + findAllHolders("shiny gold", inputLines(7)).size)
    println("Part 2: " + (totalContentsForColour("shiny gold", inputLines(7)) - 1))
}

fun runDay08() {
    val program = Program.parse(inputLines(8))
    val state = program.run()
    println("Part 1: " + state.accumulator)
    println("Part 2: " + findFixedProgramResult(program, 0))
}

fun runDay09() {
    println("Part 1: " + findFirstInvalidAfterPreamble(25, inputAsLongList(9)))
    println("Part 2: " + encryptionWeakness(25, inputAsLongList(9)))
}

fun runDay10() {
    println("Part 1: " + joltageDifferences(inputAsIntList(10)).let { it.first * it.second })
    println("Part 2: " + adapterCombinations(inputAsIntList(10)))
}

fun runDay11() {
    val grid1 = Grid.fromSpec(inputLines(11))
    println("Part 1: " + grid1.iterateUntilStable().totalOccupied())
    val grid2 = Grid.fromSpec(inputLines(11), true)
    println("Part 2: " + grid2.iterateUntilStable().totalOccupied())
}

fun runDay12() {
    println("Part 1: " + NavState().execute(inputLines(12)).manhattan)
    println("Part 2: " + NavState().waypointExecute(inputLines(12)).manhattan)
}

fun runDay13() {
    println("Part 1: " + day13Part1(inputLines(13)))
    println("Part 2: " + day13Part2(inputLines(13)))
}

fun runDay14() {
    println("Part 1: " + day14Part1(inputLines(14)))
    println("Part 2: " + day14Part2(inputLines(14)))
}

fun runDay15() {
    println("Part 1: " + day15Part1(inputLines(15).first()))
    println("Part 1: " + day15Part2(inputLines(15).first()))
}

fun runDay16() {
    println("Part 1: " + day16Part1(inputLines(16)))
    println("Part 2: " + day16Part2(inputLines(16)))
}

fun runDay17() {
    println("Part 1: " + day17Part1(inputAsText(17)))
}

fun inputForDay(dayNum: Int): Reader = File(String.format("inputs/%02d.txt", dayNum))
    .let { if (it.exists()) it.bufferedReader() else System.`in`.bufferedReader() }

fun inputLines(dayNum: Int): List<String> = inputForDay(dayNum).readLines()

fun inputAsIntList(dayNum: Int) = inputLines(dayNum).map { it.toInt() }

fun inputAsLongList(dayNum: Int) = inputLines(dayNum).map { it.toLong() }

fun inputAsText(dayNum: Int) = inputForDay(dayNum).readText()

