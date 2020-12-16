package test.advent2020

import advent2020.GameState
import advent2020.NO_STATE
import advent2020.StateInfo
import advent2020.day15Part1
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day15Spec : FunSpec({

    val start = mapOf(
        0 to Pair(1, 1),
        3 to Pair(2, 2),
        6 to Pair(3, 3),
    )

    test("GameState from string") {

        val state = GameState("0,3,6")

        state.lastValue.shouldBe(6)
        state.lastTurn.shouldBe(3)
        state.stateFor(0).shouldBe(StateInfo(1, 1))
        state.stateFor(3).shouldBe(StateInfo(2, 2))
        state.stateFor(6).shouldBe(StateInfo(3, 3))
        state.stateFor(99).shouldBe(NO_STATE)
    }

    test("Generate next states") {
        val state = GameState("0,3,6")

        val next = state.next()

        next.lastValue.shouldBe(0)
        next.lastTurn.shouldBe(4)
        next.stateFor(0).shouldBe(StateInfo(4, 1))
        next.stateFor(4).shouldBe(NO_STATE)

        val following = next.next()
        following.lastValue.shouldBe(3)
        following.lastTurn.shouldBe(5)
        following.stateFor(3).shouldBe(StateInfo(5, 2))
    }

    test("Part 1") {
        day15Part1("0,3,6").shouldBe(436)
        day15Part1("1,3,2").shouldBe(1)
        day15Part1("2,1,3").shouldBe(10)
        day15Part1("1,2,3").shouldBe(27)
        day15Part1("3,2,1").shouldBe(438)
        day15Part1("3,1,2").shouldBe(1836)
    }
})