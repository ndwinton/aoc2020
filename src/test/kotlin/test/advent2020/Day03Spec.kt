package test.advent2020

import advent2020.Day03
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day03Spec : FunSpec({
    val mapData = """
        ..##.......
        #...#...#..
        .#....#..#.
        ..#.#...#.#
        .#...##..#.
        ..#.##.....
        .#.#.#....#
        .#........#
        #.##...#...
        #...##....#
        .#..#...#.#
    """.trimIndent().split("\n")

    test("Can build a map from lines") {

        val map = Day03(mapData)

        map.height.shouldBe(11)
    }

    test("Map data should repeat indefinitely to the right") {

        val map = Day03(mapData)

        map[0, 0].shouldBe('.')
        map[0, 3].shouldBe('#')
        map[10, 10].shouldBe('#')
        map[0, 11].shouldBe('.')
        map[0, 13].shouldBe('#')
        map[0, 24].shouldBe('#')
    }

    test("Count trees for given path") {
        val map = Day03(mapData)

        map.countTrees(3, 1).shouldBe(7)
        map.countTrees(0, 1).shouldBe(3)
    }

    test("Product for multiple strategies") {
        val map = Day03(mapData)

        map.productForStrategies(listOf(Pair(3, 1), Pair(0, 1))).shouldBe(21)
    }
})