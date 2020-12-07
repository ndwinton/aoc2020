package advent2020

// I took the decision to reprocess the input each time here rather than
// parsing and building an internal representation. That's fine at this
// scale of input but isn't exactly efficient!

fun extractHoldersForColour(colour: String, rules: List<String>): List<String> =
    rules.filter { it.matches(""".* contain .*${colour}.*""".toRegex()) }
        .map { it.replace("""^(\w+ \w+) bags contain.*""".toRegex(), "\$1") }

fun findAllHolders(colour: String, rules: List<String>): List<String> =
    extractHoldersForColour(colour, rules).let { holders ->
        holders + holders.flatMap { findAllHolders(it, rules) }
    }.distinct()

fun extractContentsForColour(colour: String, rules: List<String>): List<Pair<String,Int>> =
    rules.filter { it.startsWith(colour) }
        .flatMap {
            Regex("""(\d+) (\w+ \w+)""")
                .findAll(it)
                .map { match -> Pair(match.groupValues[2], match.groupValues[1].toInt()) }
        }

// Note that this *includes* the enclosing bag itself -- but I think
// I can do the mental arithmetic to provide the required answer to
// the AoC site ;)
fun totalContentsForColour(colour: String, rules: List<String>): Int =
    extractContentsForColour(colour, rules).fold(1) { total, pair ->
        total + totalContentsForColour(pair.first, rules) * pair.second
    }
