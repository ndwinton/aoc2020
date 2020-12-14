package test.advent2020

import advent2020.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldContainInOrder
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

    test("Convert version 2 mask string to or-mask for 1 and any-mask for X longs") {
        val empty = Mask2("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
        empty.orMask.shouldBe(0b0000_00000000_00000000_00000000_00000000L)
        empty.floatingMask.shouldBe(0b1111_11111111_11111111_11111111_11111111L)

        val example = Mask2("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        example.orMask.shouldBe(0b0000_00000000_00000000_00000000_01000000L)
        example.floatingMask.shouldBe(0b1111_11111111_11111111_11111111_10111101L)
    }

    test("Parse and apply version 2") {
        parseAndApply2("""
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
        """.trimIndent().split("\n"))
            .shouldBe(mapOf(
                26L to 100L,
                27L to 100L,
                58L to 100L,
                59L to 100L,
            ))
    }

    test("Final result v2") {
        day14Part2("""
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
        """.trimIndent().split("\n")).shouldBe(208L)
    }

    test("Values from floating mask") {
        valuesFromFloatingMask(0b10101L).shouldContainExactlyInAnyOrder(listOf(
            0b0L, 0b1L, 0b100L, 0b101L, 0b10000, 0b10001L, 0b10100L, 0b10101L))
    }
})