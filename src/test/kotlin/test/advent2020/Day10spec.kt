package test.advent2020

import advent2020.adapterCombinations
import advent2020.joltageDifferences
import io.kotest.core.spec.style.FunSpec
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
        adapterCombinations(list1).shouldBe(8)
    }

    test("Combinations for large example") {
        adapterCombinations(list2).shouldBe(19208)
    }
})