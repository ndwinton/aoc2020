package test.advent2020

import advent2020.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day13Spec : FunSpec({

    test("Parsing bus IDs") {
        parseBusIds("7,13,x,x,59,x,31,19").shouldBe(listOf(7, 13, 59, 31, 19))
    }

    test("Find earliest after timestamp") {
        earliestAfterTimestampForBusId(939, 7).shouldBe(945)
        earliestAfterTimestampForBusId(939, 13).shouldBe(949)
        earliestAfterTimestampForBusId(939, 59).shouldBe(944)
    }

    test("find shortest across all IDs") {
        findShortestDelayAndId(939, listOf(7, 13, 59, 31, 19)).shouldBe(Pair(5, 59))
    }

    test("Parse to ID/offset pairs") {
        parseBusRecords("7,13,x,x,59,x,31,19").shouldBe(listOf(BusRecord(7, 0), BusRecord(13, 1), BusRecord(59, 4), BusRecord(31, 6), BusRecord(19, 7)))
    }

    test("Overall alignment period") {
        findAlignmentTime(listOf(BusRecord(17,0), BusRecord(13,2), BusRecord(19,3))).shouldBe(3417L)
        findAlignmentTime(listOf(BusRecord(67, 0), BusRecord(7, 1), BusRecord(59, 2), BusRecord(61, 3))).shouldBe(754018L)
    }

})