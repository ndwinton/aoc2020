package test.advent2020

import advent2020.PwdRecord
import advent2020.day02Part1
import advent2020.day02Part2
import advent2020.toPwdRecord
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day02Spec : FunSpec ({

    test("Parsing input text") {
        "1-3 a: abcde".toPwdRecord().shouldBe(PwdRecord(first = 1, second = 3, letter = 'a', password = "abcde"))
    }

    test("Record validity should be set based on the v1 rules") {
        val record1 = "1-3 a: abcde".toPwdRecord()
        val record2 = "1-3 b: cdefg".toPwdRecord()
        val record3 = "2-9 c: ccccccccc".toPwdRecord()

        record1.v1Valid.shouldBe(true)
        record2.v1Valid.shouldBe(false)
        record3.v1Valid.shouldBe(true)
    }

    test("Part 1 wrapper counts validity of list of inputs") {
        day02Part1(listOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )).shouldBe(2)
    }

    test("Record validity should be set based on the v2 rules") {
        val record1 = "1-3 a: abcde".toPwdRecord()
        val record2 = "1-3 b: cdefg".toPwdRecord()
        val record3 = "1-3 c: ccccccccc".toPwdRecord()

        record1.v2Valid.shouldBe(true)
        record2.v2Valid.shouldBe(false)
        record2.v2Valid.shouldBe(false)
    }

    test("Part 2 wrapper counts validity of list of inputs") {
        day02Part2(listOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )).shouldBe(1)
    }
})