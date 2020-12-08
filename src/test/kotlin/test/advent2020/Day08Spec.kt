package test.advent2020

import advent2020.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day08Spec : FunSpec({

    val input = """
        nop +0
        acc +1
        jmp +4
        acc +3
        jmp -3
        acc -99
        acc +1
        jmp -4
        acc +6
    """.trimIndent().split("\n")

    test("Parsing input to a program") {
        Program.parse(input).opCodes.shouldBe(listOf(Nop(0), Acc(1), Jmp(4), Acc(3), Jmp(-3), Acc(-99), Acc(+1), Jmp(-4), Acc(6)))
    }

    test("Running nop to completion") {
        val program = Program(listOf(Nop(20)))

        program.run()

        program.accumulator.shouldBe(0)
        program.pc.shouldBe(1)
    }

    test("Running acc to completion") {
        val program = Program(listOf(Acc(20), Acc(10)))

        program.run()

        program.accumulator.shouldBe(30)
        program.pc.shouldBe(2)
    }

    test("Running jmp to completion") {
        val program = Program(listOf(Jmp(2), Acc(1)))

        program.run()

        program.accumulator.shouldBe(0)
        program.pc.shouldBe(2)
    }

    test("Program terminates on loop") {
        val program = Program(listOf(Nop(0), Acc(10), Jmp(-1)))

        program.run()

        program.accumulator.shouldBe(10)
        program.pc.shouldBe(1)
    }

    test("Example input") {
        val program = Program.parse(input)

        program.run()

        program.accumulator.shouldBe(5)
        program.pc.shouldBe(1)
    }

    test("Termination codes") {
        Program(listOf(Nop(0))).run().shouldBe(true)    // Normal
        Program(listOf(Jmp(0))).run().shouldBe(false)   // Loop
    }

    test("Fixing program") {
        val program = Program.parse("""
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent().split("\n"))
        findFixedProgramResult(program, 0).shouldBe(8)
    }
})