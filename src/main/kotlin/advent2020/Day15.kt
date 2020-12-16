package advent2020

data class StateInfo(val lastIndex: Int, val previousIndex: Int)

val NO_STATE = StateInfo(0, 0)

class GameState(val lastValue: Int, val lastTurn: Int, val stateMap: Map<Int,StateInfo>) {

    constructor(stateString: String) : this(lastStartingNumber(stateString), lastTurnNumber(stateString), startingMap(stateString)) {
        val map = stateString.split(",")
            .mapIndexed { index, str -> Pair(str.toInt(), Pair(index + 1, index + 1)) }
            .toMap()
    }

    fun stateFor(number: Int): StateInfo = stateMap.getOrDefault(number, NO_STATE)

    fun next(): GameState {
        if (lastTurn % 1000 == 0) {
            println("$lastTurn / $lastValue / ${stateMap.size}")
        }
        val lastDiff = stateFor(lastValue).lastIndex - stateFor(lastValue).previousIndex
        val lastDiffState = stateFor(lastDiff)
        val turn = lastTurn + 1
        return if (lastDiffState == NO_STATE) {
            GameState(lastDiff, turn, stateMap + Pair(lastDiff, StateInfo(turn, turn)))
        } else {
            GameState(lastDiff, turn, stateMap + Pair(lastDiff, StateInfo(turn, lastDiffState.lastIndex)))
        }
    }
}

private fun startingMap(input: String): Map<Int,StateInfo> =
    input.split(",")
        .mapIndexed { index, str -> Pair(str.toInt(), StateInfo(index + 1, index + 1)) }
        .toMap()

private fun lastStartingNumber(input: String): Int = input.split(",").last().toInt()

private fun lastTurnNumber(input: String): Int = input.split(",").size

fun day15Part1(input: String) = generateSequence(GameState(input)) { it.next() }.dropWhile { it.lastTurn < 2020 }.first().lastValue

fun day15Part2(input: String) = generateSequence(GameState(input)) { it.next() }.dropWhile { it.lastTurn < 30000000 }.first().lastValue