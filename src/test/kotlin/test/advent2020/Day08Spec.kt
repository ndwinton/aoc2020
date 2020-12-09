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

        val state = program.run()

        state.accumulator.shouldBe(0)
        state.pc.shouldBe(1)
        state.completed.shouldBe(true)
    }

    test("Running acc to completion") {
        val program = Program(listOf(Acc(20), Acc(10)))

        val state = program.run()

        state.accumulator.shouldBe(30)
        state.pc.shouldBe(2)
        state.completed.shouldBe(true)
    }

    test("Running jmp to completion") {
        val program = Program(listOf(Jmp(2), Acc(1)))

        val state = program.run()

        state.accumulator.shouldBe(0)
        state.pc.shouldBe(2)
        state.completed.shouldBe(true)
    }

    test("Program terminates on loop") {
        val program = Program(listOf(Nop(0), Acc(10), Jmp(-1)))

        val state = program.run()

        state.accumulator.shouldBe(10)
        state.pc.shouldBe(1)
        state.completed.shouldBe(false)
    }

    test("Example input") {
        val program = Program.parse(input)

        val state = program.run()

        state.accumulator.shouldBe(5)
        state.pc.shouldBe(1)
        state.completed.shouldBe(false)
    }

    test("Termination codes") {
        Program(listOf(Nop(0))).run().completed.shouldBe(true)    // Normal
        Program(listOf(Jmp(0))).run().completed.shouldBe(false)   // Loop
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