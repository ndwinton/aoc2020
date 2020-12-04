package test.advent2020

import advent2020.containsMandatoryKeys
import advent2020.toKeyValuePairMap
import advent2020.toSingleLines
import advent2020.countValidRecords
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day04Spec : FunSpec ({
    val input = """
        ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
        byr:1937 iyr:2017 cid:147 hgt:183cm

        iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
        hcl:#cfa07d byr:1929

        hcl:#ae17e1 iyr:2013
        eyr:2024
        ecl:brn pid:760753108 byr:1931
        hgt:179cm

        hcl:#cfa07d eyr:2025 pid:166559648
        iyr:2011 ecl:brn hgt:59in
    """.trimIndent()

    test("Convert input to single-line records") {
        input.toSingleLines().shouldBe(listOf(
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm",
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929",
            "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm",
            "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in",
        ))
    }

    test("Convert line to map of key-value pairs") {
        "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
            .toKeyValuePairMap().shouldBe(mapOf(
                "ecl" to "gry",
                "pid" to "860033327",
                "eyr" to "2020",
                "hcl" to "#fffffd",
                "byr" to "1937",
                "iyr" to "2017",
                "cid" to "147",
                "hgt" to "183cm"
            ))
    }

    test("Check map contains mandatory keys") {
        val all = mapOf("ecl" to "1", "pid" to "2", "eyr" to "3", "hcl" to "4", "byr" to "5", "iyr" to "6", "cid" to "7", "hgt" to "8")
        val noCid = mapOf("ecl" to "1", "pid" to "2", "eyr" to "3", "hcl" to "4", "byr" to "5", "iyr" to "6", "hgt" to "8")
        val missing = mapOf("ecl" to "1", "pid" to "2", "eyr" to "3", "byr" to "5", "iyr" to "6", "cid" to "7", "hgt" to "8")

        all.containsMandatoryKeys().shouldBe(true)
        noCid.containsMandatoryKeys().shouldBe(true)
        missing.containsMandatoryKeys().shouldBe(false)
    }

    test("Valid record count") {
        input.countValidRecords().shouldBe(2)
    }
})