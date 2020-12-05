package test.advent2020

import advent2020.decodeColumn
import advent2020.decodePassId
import advent2020.decodeRow
import advent2020.findMissing
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day05Spec : FunSpec({
    test("Decoding row") {
        decodeRow("FBFBBFFRLR").shouldBe(44)
        decodeRow("BFFFBBFRRR").shouldBe(70)
        decodeRow("FFFBBBFRRR").shouldBe(14)
        decodeRow("BBFFBBFRLL").shouldBe(102)
    }

    test("Decoding columns") {
        decodeColumn("FBFBBFFRLR").shouldBe(5)
        decodeColumn("BFFFBBFRRR").shouldBe(7)
        decodeColumn("FFFBBBFRRR").shouldBe(7)
        decodeColumn("BBFFBBFRLL").shouldBe(4)
    }

    test("Decode pass ID") {
        decodePassId("FBFBBFFRLR").shouldBe(357)
        decodePassId("BFFFBBFRRR").shouldBe(567)
        decodePassId("FFFBBBFRRR").shouldBe(119)
        decodePassId("BBFFBBFRLL").shouldBe(820)
    }

    test("Find missing element within list") {
        findMissing(listOf(10, 7, 5, 9, 3, 6, 8)).shouldBe(4)
    }
})