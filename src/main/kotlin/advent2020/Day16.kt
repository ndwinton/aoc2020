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