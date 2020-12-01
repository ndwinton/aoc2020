package test.advent2020

import advent2020.day01
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day01Spec : FunSpec({

    test("Given a list of two numbers summing to 2020, return the product") {

        day01(1721, 299).shouldBe(514579)
        day01(1, 2019).shouldBe(2019)
    }

    test("Within a list, only two numbers summing to 2020 should be multiplied") {
        day01(1721, 979, 366, 299, 675).shouldBe(1721 * 299)
    }

    test("Should handle 1010 cases") {
        day01(1010, 1, 2019).shouldBe(1 * 2019)
        day01(1010, 1, 1010).shouldBe(1010 * 1010)
    }

    test("Should only return first match if there are multiple solutions") {
        day01(1010, 1, 1010, 2019).shouldBe(1010 * 1010)
    }
})
