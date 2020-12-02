package advent2020

data class PwdRecord(val min: Int, val max: Int, val letter: Char, val password: String) {
    val valid: Boolean = password.filter { it == letter }.length in min .. max
}

fun String.toPwdRecord(): PwdRecord {
    val (minStr, maxStr, letterStr, password) = Regex("""(\d+)-(\d+)\s+(.):\s+(\w+)""").find(this)!!.destructured
    return PwdRecord(minStr.toInt(), maxStr.toInt(), letterStr[0], password)
}
