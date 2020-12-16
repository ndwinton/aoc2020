package advent2020

fun extractRanges(input: List<String>): List<IntRange> =
    input.filter { it.contains( " or ") }
        .flatMap {
            val match = Regex("""(\d+)-(\d+) or (\d+)-(\d+)""").find(it)!!
            val (a, b, c, d) = match.destructured
            listOf((a.toInt()..b.toInt()), (c.toInt()..d.toInt()))
        }

fun extractTicketData(input: List<String>): List<List<Int>> =
    input.filter { it.contains(",") }
        .map { line -> line.split(",").map { it.toInt() }}

fun day16Part1(input: List<String>): Int {
    val ranges = extractRanges(input)
    val tickets = extractTicketData(input)

    return tickets.flatMap { ticketData ->
        ticketData.filter { number ->
            !ranges.any { it.contains(number) }
        }
    }.sum()
}

fun extractNamedRangePairs(input: List<String>): Map<String,Pair<IntRange,IntRange>> =
    input.filter { it.contains( " or ") }
        .map {
            val match = Regex("""^([^:]+): (\d+)-(\d+) or (\d+)-(\d+)""").find(it)!!
            val (name, a, b, c, d) = match.destructured
            Pair(name, Pair((a.toInt()..b.toInt()), (c.toInt()..d.toInt())))
        }.toMap()

fun extractValidTicketData(input: List<String>, ranges: List<IntRange>): List<List<Int>> =
    extractTicketData(input).filter { ticketData ->
        ticketData.all { number -> ranges.any { it.contains(number ) } } }

fun <T> List<List<T>>.transpose(): List<List<T>> {
    val min: Int = this.map { it.size }.minOf { it }
    return (0 until min).map { index ->
        this.map { it[index] }
    }
}

fun day16Part2(input: List<String>): Long {
    val myTicket = extractTicketData(input)[0]

    return findMappings(input)
        .filter { (index, name) ->
            name.startsWith("departure")
        }.keys.fold(1L) { product, index ->
            product * myTicket[index]
        }
}

fun findMappings(input: List<String>): Map<Int,String> {
    val namedPairs = extractNamedRangePairs(input)
    val ranges = extractRanges(input)
    val validData = extractValidTicketData(input, ranges)
    val transposed = validData.transpose()
    val possible = transposed.mapIndexed { index, list ->
        val keys = namedPairs.filter { (name, ranges) ->
            list.all { number -> ranges.first.contains(number) || ranges.second.contains(number) }
        }.keys.toList()
        Pair(index, keys)
    }.toMap()

    return determineKnown(possible)
}

tailrec fun determineKnown(possible: Map<Int,List<String>>, known: Map<Int,String> = emptyMap()): Map<Int,String> {
    if (possible.isEmpty()) return known

    val singles = possible.filter { (index, list) -> list.size == 1 }
    if (singles.isEmpty()) throw Exception("empty singles - more thought needed")
    val newKnownValues = singles.map { (index, list) -> list.first() }

    val newPossible = possible
        .map { (index, list) ->
            Pair(index, list.filter { !newKnownValues.contains(it) })
        }.filter { (index, list) -> !list.isEmpty() }.toMap()

    return determineKnown(newPossible, known + singles.map { Pair(it.key, it.value.first())})
}