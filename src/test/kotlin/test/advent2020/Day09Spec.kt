package test.advent2020

import advent2020.anyTwoSumToValue
import advent2020.contiguousSumForValue
import advent2020.encryptionWeakness
import advent2020.findFirstInvalidAfterPreamble
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe

class Day09Spec : FunSpec({

    test("Finding pair in list summing to value") {
        val list = listOf<Long>(35, 20, 15, 25, 47)
        anyTwoSumToValue(40, list).shouldBe(true)
        anyTwoSumToValue(67, list).shouldBe(true)
        anyTwoSumToValue(55, list).shouldBe(true)

        anyTwoSumToValue(56, list).shouldBe(false)
        anyTwoSumToValue(46, list).shouldBe(false)
    }

    val exampleList = listOf<Long>(
        35, 20, 15, 25, 47, 40, 62, 55, 65, 95,
        102, 117, 150, 182, 127, 219, 299, 277, 309, 576,
    )

    test("Finding first invalid number") {
        findFirstInvalidAfterPreamble(5, exampleList).shouldBe(127)
    }

    test("Contiguous sum when not possible gives empty list") {
        contiguousSumForValue(42, listOf<Long>(1, 2, 43)).shouldBeEmpty()
    }

    test("Contiguous sum gives list of values") {
        contiguousSumForValue(42, listOf(1, 2, 40, 25)).shouldBe(listOf<Long>(2, 40))

        contiguousSumForValue(127, exampleList).shouldBe(listOf<Long>(15, 25, 47, 40))
    }

    test("Encryption weakness") {
        encryptionWeakness(5, exampleList).shouldBe(62L)
    }
})