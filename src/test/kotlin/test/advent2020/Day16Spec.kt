package test.advent2020

import advent2020.day16Part1
import advent2020.extractRanges
import advent2020.extractTicketData
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day16Spec : FunSpec({

    val input = """
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

    test("Extract ranges from input") {
        extractRanges(input).shouldBe(listOf((1..3), (5..7), (6..11), (33..44), (13..40), (45..50)))
    }

    test("Extarct ticket data from input") {
        extractTicketData(input).shouldBe(listOf(
            listOf(7, 1, 14),
            listOf(7, 3, 47),
            listOf(40, 4, 50),
            listOf(55, 2, 20),
            listOf(38, 6, 12)
        ))
    }

    test("Part 1") {
        day16Part1(input).shouldBe(71)
    }
})