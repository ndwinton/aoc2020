package advent2020

fun String.toSingleLines() =
    this.split("""(?m)^\s*$""".toRegex()).map { it.replace("\n", " ").trim() }

fun String.toKeyValuePairMap() =
    this.split("""\s+""".toRegex()).map { part ->
        part.split(":").let { Pair(it[0], it[1]) }
    }.toMap()

fun Map<String,String>.containsMandatoryKeys() =
    this.keys.containsAll(listOf("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt"))

fun String.countValidRecords() =
    this.toSingleLines()
        .filter { it.toKeyValuePairMap().containsMandatoryKeys() }
        .count()