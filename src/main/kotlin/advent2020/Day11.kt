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


class Grid(val grid: List<List<Cell>>) {
    val rows: Int = grid.size
    val columns: Int = grid[0].size

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
                if (cell == Empty && surrounding == 0) Full else if (cell == Full && surrounding >= 4) Empty else cell
            }
        })

    tailrec fun iterateUntilStable(previous: Grid = this): Grid {
        val next = previous.iterate()

        return if (next == previous) return next
        else iterateUntilStable(next)
    }

    private fun adjacentOccupied(rowIndex: Int, colIndex: Int): Int =
        get(rowIndex, colIndex - 1).occupancy +
                get(rowIndex, colIndex + 1).occupancy +
                get(rowIndex - 1, colIndex).occupancy +
                get(rowIndex - 1, colIndex - 1).occupancy +
                get(rowIndex - 1, colIndex + 1).occupancy +
                get(rowIndex + 1, colIndex).occupancy +
                get(rowIndex + 1, colIndex - 1).occupancy +
                get(rowIndex + 1, colIndex + 1).occupancy

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
        fun fromSpec(gridSpec: List<String>) = Grid(gridSpec.map { row -> row.map { charToCell(it) } })

        private fun charToCell(char: Char): Cell =
            when (char) {
                'L' -> Empty
                '#' -> Full
                else -> Floor
            }
    }
}