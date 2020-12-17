package advent2020

data class Point(val x: Int, val y:Int, val z: Int, val w: Int = 0)

class CellMap(val state: Set<Point>, val use4d: Boolean = false) {
    val minima: Point
    val maxima: Point

    init {
        minima = Point(state.minOf { it.x }, state.minOf { it.y }, state.minOf { it.z }, state.minOf { it.w })
        maxima = Point(state.maxOf { it.x }, state.maxOf { it.y }, state.maxOf { it.z }, state.maxOf { it.w })
    }

    fun neighbours(point: Point): Int = (point.x - 1 .. point.x + 1).flatMap { x ->
        (point.y - 1 .. point.y + 1).flatMap { y ->
            (point.z - 1 .. point.z + 1).flatMap { z ->
                val wRange = if (use4d) (point.w - 1 .. point.w + 1) else (0 .. 0)
                wRange.map { w ->
                    if (Point(x, y, z, w).let { state.contains(it) && it != point }) 1 else 0
                }
            }
        }
    }.sum()

    fun next(): CellMap = CellMap(
        (minima.x - 1 .. maxima.x + 1).flatMap { x ->
            (minima.y - 1 .. maxima.y + 1).flatMap { y ->
                (minima.z - 1 .. maxima.z + 1).flatMap { z -> // Strictly, map is symmetrical in w and z, so we could optimize
                    val wRange = if (use4d) (minima.w - 1 .. maxima.w + 1) else (0 .. 0)
                    wRange.flatMap { w ->
                        val point = Point(x, y, z, w)
                        when (neighbours(point)) {
                            3 -> listOf(point)
                            2 -> if (state.contains(point)) listOf(point) else emptyList()
                            else -> emptyList()
                        }
                    }
                }
            }
        }.toSet(), use4d
    )

    fun iterate(iterations: Int) = generateSequence(this) { it.next() }.drop(iterations).first()

    override fun toString(): String {
        return "CellMap(minima = $minima, maxima = $maxima, use4d = ${use4d}, state =\n" +
                (minima.w .. maxima.w).map { w ->
                    (minima.z..maxima.z).map { z ->
                        (minima.y..maxima.y).map { y ->
                            (minima.x..maxima.x).map { x ->
                                if (state.contains(Point(x, y, z))) "#" else "."
                            }.joinToString("")
                        }
                    }.map { it.joinToString("\n") }
                }.map { it.joinToString("\n---\n") }.joinToString("\n===\n") + "\n)"
    }

    companion object {
        fun parseStartMap(input: String, use4d: Boolean = false): CellMap =
            CellMap(
                input.split("\n")
                    .flatMapIndexed { y, line ->
                        line.mapIndexed { x, ch -> Pair(x, ch) }
                            .filter { it.second == '#' }
                            .map { Point(it.first, y, 0) }
                }.toSet(), use4d
            )
    }
}

private fun activeCountAfterIterations(map: CellMap, iterations: Int): Int = map.iterate(iterations).state.size

fun day17Part1(input: String) = activeCountAfterIterations(CellMap.parseStartMap(input), 6)

fun day17Part2(input: String) = activeCountAfterIterations(CellMap.parseStartMap(input, true), 6)
