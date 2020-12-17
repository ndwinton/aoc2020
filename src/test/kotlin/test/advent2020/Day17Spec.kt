package test.advent2020

import advent2020.CellMap
import advent2020.Point
import advent2020.day17Part1
import advent2020.day17Part2
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day17Spec : FunSpec({

    val input = """
        .#.
        ..#
        ###
    """.trimIndent()

    val gen2 = CellMap(setOf(
        // #..
        // ..#
        // .#.
        Point(0, 0, -1),
        Point(2, 1, -1),
        Point(1, 2, -1),


        // #.#
        // .##
        // .#.
        Point(0, 0, 0),
        Point(2, 0, 0),
        Point(1, 1, 0),
        Point(2, 1, 0),
        Point(1, 2, 0),

        // #..
        // ..#
        // .#.
        Point(0, 0, 1),
        Point(2, 1, 1),
        Point(1, 2, 1),
    ))

    test("Parsing cell map to object") {
        val map = CellMap.parseStartMap(input)

        map.state.shouldBe(setOf(
            Point(1, 0, 0),
            Point(2, 1, 0),
            Point(0, 2, 0),
            Point(1, 2, 0),
            Point(2, 2, 0),
        ))
    }

    test("Bounding box of map") {
        val map = CellMap(setOf(
            Point(1, 1, 1),
            Point(-1, 2, 3),
            Point(3, -2, 3)
        ))

        map.minima.shouldBe(Point(-1, -2, 1))
        map.maxima.shouldBe(Point(3, 2, 3))
    }

    test("Calculating cell neighbours") {

        gen2.neighbours(Point(0, 0, 0)).shouldBe(3)
        gen2.neighbours(Point(0, 0, -1)).shouldBe(2)
        gen2.neighbours(Point(0, 0, 1)).shouldBe(2)

        gen2.neighbours(Point(1, 1, 0)).shouldBe(10)
        gen2.neighbours(Point(1, 1, 1)).shouldBe(8)
    }

    test("Iterating the map one step") {
        val start = CellMap.parseStartMap(input)

        val result = start.next()

        result.state.shouldBe(setOf(
            Point(x=0, y=1, z=-1),
            Point(x=0, y=1, z=0),
            Point(x=0, y=1, z=1),
            Point(x=1, y=2, z=0),
            Point(x=1, y=3, z=-1),
            Point(x=1, y=3, z=0),
            Point(x=1, y=3, z=1),
            Point(x=2, y=1, z=0),
            Point(x=2, y=2, z=-1),
            Point(x=2, y=2, z=0),
            Point(x=2, y=2, z=1)
        ))
    }

    test("Part 1") {
        day17Part1(input).shouldBe(112)
    }

    test("4 dimensions") {
        val start = CellMap.parseStartMap(input, true)

        val result = start.iterate(6)

        result.state.size.shouldBe(848)
    }

    test("Part 2") {
        day17Part2(input).shouldBe(848)
    }
})