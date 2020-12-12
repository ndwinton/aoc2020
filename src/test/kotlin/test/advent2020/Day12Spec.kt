package test.advent2020

import advent2020.NavState
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day12Spec : FunSpec({

    test("Default state") {
        val state = NavState()

        state.x.shouldBe(0)
        state.y.shouldBe(0)
        state.dx.shouldBe(1)
        state.dy.shouldBe(0)
    }

    test("Stepping N/S/E/W") {
        val state = NavState()

        state.step("N5").shouldBe(NavState(x = 0, y = 5, dx = 1, dy = 0))
        state.step("S7").shouldBe(NavState(x = 0, y = -7, dx = 1, dy = 0))
        state.step("E17").shouldBe(NavState(x = 17, y = 0, dx = 1, dy = 0))
        state.step("W1").shouldBe(NavState(x = -1, y = 0, dx = 1, dy = 0))
    }

    test("Stepping forward") {
        NavState(dx = 1, dy = 0).step("F10").shouldBe(NavState(x = 10, y = 0, dx = 1, dy = 0))
        NavState(dx = -1, dy = 0).step("F10").shouldBe(NavState(x = -10, y = 0, dx = -1, dy = 0))
        NavState(dx = 0, dy = 1).step("F10").shouldBe(NavState(x = 0, y = 10, dx = 0, dy = 1))
        NavState(dx = 0, dy = -1).step("F10").shouldBe(NavState(x = 0, y = -10, dx = 0, dy = -1))
    }

    test("Turning") {
        val state = NavState()

        state.step("R90").shouldBe(NavState(x = 0, y = 0, dx = 0, dy = -1))
        state.step("R180").shouldBe(NavState(x = 0, y = 0, dx = -1, dy = 0))
        state.step("R270").shouldBe(NavState(x = 0, y = 0, dx = 0, dy = 1))
        state.step("L90").shouldBe(NavState(x = 0, y = 0, dx = 0, dy = 1))
        state.step("L180").shouldBe(NavState(x = 0, y = 0, dx = -1, dy = 0))
        state.step("L270").shouldBe(NavState(x = 0, y = 0, dx = 0, dy = -1))
    }

    test("Execute steps") {
        val state = NavState()

        state.execute(listOf("N1", "E2", "R90", "F3")).shouldBe(NavState(x = 2, y = -2, dx = 0, dy = -1))
    }

    test("Storing waypoint state") {
        NavState().wx.shouldBe(10)
        NavState().wy.shouldBe(1)

        NavState(wx = 20).wx.shouldBe(20)
        NavState(wy = 20).wy.shouldBe(20)
    }
    test("Waypoint stepping N/S/E/W") {
        val state = NavState()

        state.waypointStep("N5").shouldBe(NavState(wx = 10, wy = 6))
        state.waypointStep("S7").shouldBe(NavState(wx = 10, wy = -6))
        state.waypointStep("E17").shouldBe(NavState(wx = 27, wy = 1))
        state.waypointStep("W1").shouldBe(NavState(wx = 9, wy = 1))
    }

    test("Waypoint stepping forward") {
        NavState().waypointStep("F10").shouldBe(NavState(x = 100, y = 10))
        NavState(wx = 2, wy = -1).waypointStep("F10").shouldBe(NavState(x = 20, y = -10, wx = 2, wy = -1))
    }

    test("Waypoint rotation") {
        val state = NavState(wx = 10, wy = 4)

        state.waypointStep("R90").shouldBe(NavState(wx = 4, wy = -10))
    }
})