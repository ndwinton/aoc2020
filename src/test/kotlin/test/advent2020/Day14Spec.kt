package test.advent2020

import advent2020.Mask
import advent2020.parseAndApply
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day14Spec : FunSpec({

    test("Convert mask string to or-mask for 1 and and-mask for 0 longs") {
        val empty = Mask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
        empty.orMask.shouldBe(0b0000_00000000_00000000_00000000_00000000L)
        empty.andMask.shouldBe(0b1111_11111111_11111111_11111111_11111111L)

        val example = Mask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        example.orMask.shouldBe(0b0000_00000000_00000000_00000000_01000000L)
        example.andMask.shouldBe(0b1111_11111111_11111111_11111111_11111101L)
    }

    test("Parse and apply") {
        parseAndApply("""
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent().split("\n"))
            .shouldBe(mapOf(
                "mem[8]" to 64L,
                "mem[7]" to 101L
            ))
    }
})