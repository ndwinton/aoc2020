package test.advent2020

import advent2020.day01
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day01Spec : FunSpec({

    test("Given a list of two numbers summing to 2020, return the product") {

        day01(2, 2020, 1721, 299).shouldBe(listOf(514579))
        day01(2, 2020,1, 2019).shouldBe(listOf(2019))
    }

    test("Within a list, only two numbers summing to 2020 should be multiplied") {
        day01(2, 2020, 1721, 979, 366, 299, 675).shouldBe(listOf(1721 * 299))
    }

    test("For one number should return the target from the list or empty otherwise") {
        day01(1, 1900, 1, 2, 1901, 1900, 10).shouldBe(listOf(1900))
        day01(1, 1900, 1, 2, 2020).shouldBe(emptyList())
    }

    test("Should be able to pick 3 numbers instead of 2") {
        day01(3, 2020, 1721, 979, 366, 299, 675).shouldBe(listOf(979 * 366  * 675))
    }

    test("Should handle repeated number cases") {
        day01(2, 2020, 1010, 1, 2019).shouldBe(listOf(1 * 2019))
        day01(2, 2020, 1010, 1, 1010).shouldBe(listOf(1010 * 1010))
        day01(5, 2020, 1, 1, 1, 1, 2016).shouldBe(listOf(2016))
    }

    test("Should return distinct multiple solutions if present") {
        day01(2, 2020, 1011, 1, 1009, 2019).shouldBe(listOf(1011 * 1009, 2019))
    }
})
