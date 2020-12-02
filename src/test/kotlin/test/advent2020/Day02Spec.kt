package test.advent2020

import advent2020.PwdRecord
import advent2020.toPwdRecord
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day02Spec : FunSpec ({

    test("Parsing input text") {
        "1-3 a: abcde".toPwdRecord().shouldBe(PwdRecord(min = 1, max = 3, letter = 'a', password = "abcde"))
    }

    test("Record validity should be set based on the rules") {
        val record1 = "1-3 a: abcde".toPwdRecord()
        val record2 = "1-3 b: cdefg".toPwdRecord()

        record1.valid.shouldBe(true)
        record2.valid.shouldBe(false)
    }
})