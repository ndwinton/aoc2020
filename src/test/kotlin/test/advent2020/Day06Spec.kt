package test.advent2020

import advent2020.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day06Spec : FunSpec({
    test("Distict question in a group") {
        distinctQuestionsInGroup("""abcx
            |abcy
            |abcz""".trimMargin()).shouldBe(6)
    }
    test("Counting unique values in multiple group") {
        totalDistinctForGroups("""
                abc

                a
                b
                c

                ab
                ac

                a
                a
                a
                a

                b""".trimIndent()).shouldBe(11)
    }

    test("Count of common elements in group") {
        commonQuestionsInGroup("""abcx
            |abcy
            |abcz""".trimMargin()).shouldBe(3)
    }

    test("Counting common values in multiple group") {
        totalCommonForGroups("""
                abc

                a
                b
                c

                ab
                ac

                a
                a
                a
                a

                b""".trimIndent()).shouldBe(6)
    }
})