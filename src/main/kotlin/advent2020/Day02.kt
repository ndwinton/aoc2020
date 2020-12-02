package advent2020

fun day02Part1(lines: List<String>): Int = lines.filter { it.toPwdRecord().v1Valid }.count()

fun day02Part2(lines: List<String>): Int = lines.filter { it.toPwdRecord().v2Valid }.count()

data class PwdRecord(val first: Int, val second: Int, val letter: Char, val password: String) {

    val v1Valid: Boolean = password.filter { it == letter }.length in first .. second

    val v2Valid: Boolean = password.mapIndexed { index, char ->
        Pair(index + 1, char)
    }.filter { (position, char) ->
        char == letter && (position == first || position == second)
    }.count() == 1
}

fun String.toPwdRecord(): PwdRecord {
    val (firstStr, secondStr, letterStr, password) = Regex("""(\d+)-(\d+)\s+(.):\s+(\w+)""").find(this)!!.destructured
    return PwdRecord(firstStr.toInt(), secondStr.toInt(), letterStr[0], password)
}
