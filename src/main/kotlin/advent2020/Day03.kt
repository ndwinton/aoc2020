package advent2020

class Day03(mapData: List<String>) {
    private val map = mapData.toTypedArray()
    val height = mapData.size
    private val width = mapData[0].length

    operator fun get(row: Int, col: Int): Char = map[row][col % width]

    tailrec fun countTrees(across: Int, down: Int, startRow: Int = 0, startCol: Int = 0, count: Int = 0): Int =
        when {
            startRow + down >= height -> count
            this[startRow + down, startCol + across] == '#' -> countTrees(across, down, startRow + down, startCol + across, count + 1)
            else -> countTrees(across, down, startRow + down, startCol + across, count)
        }

    fun productForStrategies(strategies: List<Pair<Int, Int>>): Long = strategies
            .map { (across, down) -> countTrees(across, down) }
            .fold(1L) { product, value -> product * value }
}