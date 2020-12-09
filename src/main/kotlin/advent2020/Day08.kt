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

data class ProgramState(val accumulator: Int, val pc: Int, val completed: Boolean)

class Program(val opCodes: List<OpCode>) {

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

    tailrec fun run(accumulator: Int = 0, pc: Int = 0, visited: Set<Int> = emptySet()): ProgramState =
        when {
            visited.contains(pc) -> ProgramState(accumulator, pc, false)
            pc >= opCodes.size -> ProgramState(accumulator, pc, true)
            opCodes[pc] is Acc -> run(accumulator + opCodes[pc].arg, pc + 1, visited + pc)
            opCodes[pc] is Jmp -> run(accumulator, pc + opCodes[pc].arg, visited + pc)
            else -> run(accumulator, pc + 1, visited + pc)
        }
}

tailrec fun findFixedProgramResult(program: Program, opIndex: Int, newProgram: Program = program): Int {
    val state = newProgram.run()
    return if (state.completed) state.accumulator
    else findFixedProgramResult(program, opIndex + 1, Program(mutateOpCodeList(program.opCodes, opIndex)))
}

fun mutateOpCodeList(opCodes: List<OpCode>, opIndex: Int) =
    opCodes.take(opIndex) + mutateOpCode(opCodes[opIndex]) + opCodes.drop(opIndex + 1)

fun mutateOpCode(opCode: OpCode) =
    when (opCode) {
        is Nop -> Jmp(opCode.arg)
        is Acc -> opCode
        is Jmp -> Nop(opCode.arg)
    }

