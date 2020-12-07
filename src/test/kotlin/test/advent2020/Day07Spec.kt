package test.advent2020

import advent2020.extractContentsForColour
import advent2020.extractHoldersForColour
import advent2020.findAllHolders
import advent2020.totalContentsForColour
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class Day07Spec : FunSpec({
    val rules = """light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.""".split("\n")

    test("Extract holders for colour") {
        extractHoldersForColour("shiny gold", rules).shouldBe(listOf("bright white", "muted yellow"))
    }

    test("Find all holders") {
        findAllHolders("shiny gold", rules).shouldContainExactlyInAnyOrder(
            "bright white",
            "muted yellow",
            "dark orange",
            "light red",
        )
    }

    test("Extract contents of coloured bag") {
        extractContentsForColour("shiny gold", rules).shouldBe(listOf(Pair("dark olive", 1), Pair("vibrant plum", 2)))
        extractContentsForColour("faded blue", rules)
            .shouldBeEmpty()
    }

    test("Total contents of bag") {
        totalContentsForColour("shiny gold", rules).shouldBe(33)
        totalContentsForColour("shiny gold", """shiny gold bags contain 2 dark red bags.
dark red bags contain 2 dark orange bags.
dark orange bags contain 2 dark yellow bags.
dark yellow bags contain 2 dark green bags.
dark green bags contain 2 dark blue bags.
dark blue bags contain 2 dark violet bags.
dark violet bags contain no other bags.""".split("\n")).shouldBe(127)
    }
})