package advent2020

sealed class Cell(val empty: Boolean, val chair: Boolean) {
    val occupancy = if (empty) 0 else 1

    fun toSpec() = when {
        !chair -> "."
        empty -> "L"
        else -> "#"
    }
}

object Full : Cell(empty = false, chair = true)
object Empty : Cell(empty = true, chair = true)
object Floor : Cell(empty = true, chair = false)


class Grid(val grid: List<List<Cell>>, val part2Rules: Boolean = false) {
    val rows: Int = grid.size
    val columns: Int = grid[0].size
    private val toleration: Int = if (part2Rules) 5 else 4

    operator fun get(row: Int, col: Int): Cell =
        if (row in 0 until rows && col in 0 until columns) grid[row][col]
        else Floor

    fun toSpec() =
        grid.joinToString(separator = "\n") { row ->
            row.joinToString(separator = "") { it.toSpec() }
        }

    fun iterate(): Grid =
        Grid(grid.mapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, cell ->
                val surrounding = adjacentOccupied(rowIndex, colIndex)
                if (cell == Empty && surrounding == 0) Full else if (cell == Full && surrounding >= toleration) Empty else cell
            }
        }, part2Rules)

    tailrec fun iterateUntilStable(previous: Grid = this, count: Int = 0): Grid {
        val next = previous.iterate()

        return if (next == previous) { println("Interations: $count"); next }
        else iterateUntilStable(next, count + 1)
    }

    private fun adjacentOccupied(rowIndex: Int, colIndex: Int): Int =
        if (part2Rules) part2Adjacency(rowIndex, colIndex)
    else part1Adjacency(rowIndex, colIndex)

    private fun part1Adjacency(rowIndex: Int, colIndex: Int): Int =
        get(rowIndex, colIndex - 1).occupancy +
                get(rowIndex, colIndex + 1).occupancy +
                get(rowIndex - 1, colIndex).occupancy +
                get(rowIndex - 1, colIndex - 1).occupancy +
                get(rowIndex - 1, colIndex + 1).occupancy +
                get(rowIndex + 1, colIndex).occupancy +
                get(rowIndex + 1, colIndex - 1).occupancy +
                get(rowIndex + 1, colIndex + 1).occupancy

    private fun part2Adjacency(rowIndex: Int, colIndex: Int): Int =
        sliceOccupancy(rowIndex, colIndex, -1, -1) +
        sliceOccupancy(rowIndex, colIndex, -1, 0) +
        sliceOccupancy(rowIndex, colIndex, -1, 1) +
        sliceOccupancy(rowIndex, colIndex, 0, -1) +
        sliceOccupancy(rowIndex, colIndex, 0, 1) +
        sliceOccupancy(rowIndex, colIndex, 1, -1) +
        sliceOccupancy(rowIndex, colIndex, 1, 0) +
        sliceOccupancy(rowIndex, colIndex, 1, 1)


    fun sliceOccupancy(row: Int, col: Int, rowDirection: Int, colDirection: Int): Int {
        val pairSeq = generateSequence(Pair(row + rowDirection, col + colDirection)) { Pair(it.first + rowDirection, it.second + colDirection) }
        val chairPair = pairSeq
            .takeWhile { it.first in 0 until rows && it.second in 0 until columns }
            .firstOrNull { get(it.first, it.second).chair }

        return if (chairPair == null) 0 else get(chairPair.first, chairPair.second).occupancy
    }

    fun totalOccupied(): Int = grid.sumOf { row -> row.sumBy { it.occupancy } }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Grid

        return grid.zip(other.grid).all { it.first == it.second }
    }

    override fun hashCode(): Int {
        return grid.hashCode()
    }


    companion object {
        fun fromSpec(gridSpec: List<String>, part2Rules: Boolean = false) = Grid(gridSpec.map { row -> row.map { charToCell(it) } }, part2Rules)

        private fun charToCell(char: Char): Cell =
            when (char) {
                'L' -> Empty
                '#' -> Full
                else -> Floor
            }
    }
}