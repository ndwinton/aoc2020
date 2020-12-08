package advent2020

import java.lang.IllegalArgumentException

sealed class OpCode(val arg: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OpCode

        if (arg != other.arg) return false

        return true
    }

    override fun hashCode(): Int {
        return arg
    }
}

class Nop(arg: Int) : OpCode(arg)
class Acc(arg: Int) : OpCode(arg)
class Jmp(arg: Int) : OpCode(arg)

// OK, so I could go for a pure functional approach to modelling a CPU
// but mutable state makes much more sense here ...
class Program(val opCodes: List<OpCode>, var accumulator: Int = 0, var pc: Int = 0) {

    companion object {
        fun parse(lines: List<String>) =
            Program(lines.map {
                val (op, arg) = it.split(" ")
                when (op) {
                    "nop" -> Nop(arg.toInt())
                    "acc" -> Acc(arg.toInt())
                    "jmp" -> Jmp(arg.toInt())
                    else -> throw IllegalArgumentException(it)
                }
            })
    }

    fun run(): Boolean {
        val visited = BooleanArray(opCodes.size) { false }

        while (pc < opCodes.size && !visited[pc]) {
            val op = opCodes[pc]
            visited[pc] = true
            when (op) {
                is Nop -> pc++
                is Acc -> {
                    accumulator += op.arg
                    pc++
                }
                is Jmp -> pc += op.arg
            }
        }
        return pc >= opCodes.size
    }
}

tailrec fun findFixedProgramResult(program: Program, opIndex: Int, newProgram: Program = program): Int =
    if (newProgram.run()) newProgram.accumulator
    else findFixedProgramResult(program, opIndex + 1, Program(mutateOpCodeList(program.opCodes, opIndex)))

fun mutateOpCodeList(opCodes: List<OpCode>, opIndex: Int) =
    opCodes.take(opIndex) + mutateOpCode(opCodes[opIndex]) + opCodes.drop(opIndex + 1)

fun mutateOpCode(opCode: OpCode) =
    when (opCode) {
        is Nop -> Jmp(opCode.arg)
        is Acc -> opCode
        is Jmp -> Nop(opCode.arg)
    }

