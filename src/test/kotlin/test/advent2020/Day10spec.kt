package test.advent2020

import advent2020.joltageCombinations
import advent2020.joltageDifferences
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class Day10spec : FunSpec({
    val list1 = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent().split("\n").map { it.toInt() }

    val list2 = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent().split("\n").map { it.toInt() }

    test("Differences for small example") {

        joltageDifferences(list1).shouldBe(Pair(7, 5))
    }

    test("Larger example") {

        joltageDifferences(list2).shouldBe(Pair(22, 10))
    }

    test("Combinations for small example") {
        joltageCombinations(list1).shouldBe(8)
    }

    test("Combinations for large example") {
        joltageCombinations(list2).shouldBe(19208)
    }

    test("Serial combinations") {
        joltageCombinations(listOf(1)).shouldBe(1)
        joltageCombinations(listOf(1, 2)).shouldBe(2)
        joltageCombinations(listOf(1, 2, 3)).shouldBe(4)
        joltageCombinations(listOf(1, 2, 3, 4)).shouldBe(7)
        joltageCombinations(listOf(1, 2, 3, 4, 5)).shouldBe(13)
        joltageCombinations(listOf(1, 2, 3, 4, 5, 6)).shouldBe(24)
        joltageCombinations(listOf(1, 2, 3, 4, 5, 6, 7)).shouldBe(44)
    }
})