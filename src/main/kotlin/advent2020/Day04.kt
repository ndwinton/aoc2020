package advent2020

// Today let's try the everything-as-an-extension-function approach, just for fun ...

fun String.toSingleLines() =
    this.split("""(?m)^\s*$""".toRegex()).map { it.replace("\n", " ").trim() }

fun String.toKeyValuePairMap() =
    this.split("""\s+""".toRegex()).map { part ->
        part.split(":").let { Pair(it[0], it[1]) }
    }.toMap()

fun Map<String,String>.containsMandatoryKeys() =
    this.keys.containsAll(listOf("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt"))

fun String.toRecordsWithMandatoryKeys() =
    this.toSingleLines()
        .map { it.toKeyValuePairMap() }
        .filter { it.containsMandatoryKeys() }

fun Pair<String,String>.validateField() = when {
    first == "byr" && second.matches("""\d{4}""".toRegex()) -> second.toInt() in 1920 .. 2002
    first == "iyr" && second.matches("""\d{4}""".toRegex()) -> second.toInt() in 2010 .. 2020
    first == "eyr" && second.matches("""\d{4}""".toRegex()) -> second.toInt() in 2020 .. 2030
    first == "hgt" && second.matches("""\d+in""".toRegex()) -> second.replace("in", "").toInt() in 59 .. 76
    first == "hgt" && second.matches("""\d+cm""".toRegex()) -> second.replace("cm", "").toInt() in 150 .. 193
    first == "hcl" -> second.matches("""#[0-9a-f]{6}""".toRegex())
    first == "ecl" -> arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(second)
    first == "pid" -> second.matches("""\d{9}""".toRegex())
    first == "cid" -> true
    else -> false
}

fun Map<String,String>.validatePassportData() =
    !this.map { (key, value) -> Pair(key, value).validateField() }.contains(false)

fun String.countValidPassportRecords() =
    this.toRecordsWithMandatoryKeys().filter { it.validatePassportData() }.count()

