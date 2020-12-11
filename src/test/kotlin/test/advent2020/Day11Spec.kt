package test.advent2020

import advent2020.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day11Spec : FunSpec({

    test("Creating grid from spec") {
        val grid = Grid.fromSpec(listOf("LL.", "L#L", "L.#"))

        grid.rows.shouldBe(3)
        grid.columns.shouldBe(3)

        grid[0, 0].empty.shouldBe(true)
        grid[0, 0].chair.shouldBe(true)

        grid[0, 2].empty.shouldBe(true)
        grid[0, 2].chair.shouldBe(false)

        grid[1, 1].empty.shouldBe(false)
        grid[1, 1].chair.shouldBe(true)
    }

    test("Accessing outside grid should give empty result") {
        val grid = Grid.fromSpec(listOf("LL.", "L#L", "L.#"))

        grid[-1, -1].empty.shouldBe(true)
        grid[3, 3].empty.shouldBe(true)
        grid[-1, -1].chair.shouldBe(false)
    }

    test("Creating grid directly from cells") {
        val grid = Grid(
            listOf(
                listOf(Empty, Floor),
                listOf(Floor, Full)
            )
        )

        grid[0, 0].empty.shouldBe(true)
        grid[0, 1].chair.shouldBe(false)
        grid.toSpec().shouldBe("L.\n.#")
    }

    test("Iterating a grid") {
        val spec = """
            #.LL.L#.##
            #LLLLLL.L#
            L.L.L..L..
            #LLL.LL.L#
            #.LL.LL.LL
            #.LLLL#.##
            ..L.L.....
            #LLLLLLLL#
            #.LLLLLL.L
            #.#LLLL.##
        """.trimIndent().split("\n")

        val grid = Grid.fromSpec(spec)

        val iterated = grid.iterate()

        iterated.toSpec().shouldBe("""
            #.##.L#.##
            #L###LL.L#
            L.#.#..#..
            #L##.##.L#
            #.##.LL.LL
            #.###L#.##
            ..#.#.....
            #L######L#
            #.LL###L.L
            #.#L###.##
        """.trimIndent())
    }

    test("Total occupied after stability") {
        val grid = Grid.fromSpec("""
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """.trimIndent().split("\n"))

        val result = grid.iterateUntilStable()

        result.toSpec().shouldBe("""
            #.#L.L#.##
            #LLL#LL.L#
            L.#.L..#..
            #L##.##.L#
            #.#L.LL.LL
            #.#L#L#.##
            ..L.L.....
            #L#L##L#L#
            #.LLLLLL.L
            #.#L#L#.##
        """.trimIndent())

        result.totalOccupied().shouldBe(37)
    }
})