package test.advent2020

import advent2020.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day16Spec : FunSpec({

    val example1 = """
        class: 1-3 or 5-7
        row: 6-11 or 33-44
        seat: 13-40 or 45-50

        your ticket:
        7,1,14

        nearby tickets:
        7,3,47
        40,4,50
        55,2,20
        38,6,12
    """.trimIndent().split("\n")

    val example2 = """
        class: 0-1 or 4-19
        row: 0-5 or 8-19
        seat: 0-13 or 16-19

        your ticket:
        11,12,13

        nearby tickets:
        3,9,18
        15,1,5
        5,14,9
    """.trimIndent().split("\n")

    test("Extract ranges from input") {
        extractRanges(example1).shouldBe(listOf((1..3), (5..7), (6..11), (33..44), (13..40), (45..50)))
    }

    test("Extract ticket data from input") {
        extractTicketData(example1).shouldBe(listOf(
            listOf(7, 1, 14),
            listOf(7, 3, 47),
            listOf(40, 4, 50),
            listOf(55, 2, 20),
            listOf(38, 6, 12)
        ))
    }

    test("Part 1") {
        day16Part1(example1).shouldBe(71)
    }

    test("Extract named range pairs") {
        extractNamedRangePairs(example2).shouldBe(mapOf(
            "class" to Pair(0..1, 4..19),
            "row" to Pair(0..5, 8..19),
            "seat" to Pair(0..13, 16..19)
        ))
    }

    test("Extracting valid ticket data") {
        val ranges = extractRanges(example1)

        extractValidTicketData(example1, ranges).shouldBe(listOf(
            listOf(7, 1, 14),
            listOf(7, 3, 47)
        ))
    }

    test("Transposition") {
        listOf(
            listOf(7, 1, 14),
            listOf(7, 3, 47)
        ).transpose().shouldBe(listOf(
            listOf(7, 7),
            listOf(1, 3),
            listOf(14, 47)
        ))
    }

    test("Find mappings") {
        findMappings(example2).shouldBe(mapOf(
            0 to "row",
            1 to "class",
            2 to "seat"
        ))
    }
})