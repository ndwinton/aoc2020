package advent2020

import kotlin.math.absoluteValue

class NavState(val x: Int = 0, val y: Int = 0, val dx: Int = 1, val dy: Int = 0, val wx: Int = 10, val wy: Int = 1) {
    val manhattan
        get() = x.absoluteValue + y.absoluteValue

    fun step(instruction: String): NavState = when (instruction.first()) {
        'N' -> NavState(x, y + insValue(instruction), dx, dy)
        'S' -> NavState(x, y - insValue(instruction), dx, dy)
        'E' -> NavState(x + insValue(instruction), y, dx, dy)
        'W' -> NavState(x - insValue(instruction), y, dx, dy)
        'F' -> NavState(x + dx * insValue(instruction), y + dy * insValue(instruction), dx, dy)
        'R' -> rotateRight(insValue(instruction))
        'L' -> rotateLeft(insValue(instruction))
        else -> this
    }

    fun waypointStep(instruction: String): NavState = when (instruction.first()) {
        'N' -> NavState(x, y, dx, dy, wx, wy + insValue(instruction))
        'S' -> NavState(x, y, dx, dy, wx, wy - insValue(instruction))
        'E' -> NavState(x, y, dx, dy, wx + insValue(instruction), wy)
        'W' -> NavState(x, y, dx, dy, wx - insValue(instruction), wy)
        'F' -> NavState(x + wx * insValue(instruction), y + wy * insValue(instruction), dx, dy, wx, wy)
        'R' -> rotateWaypointRight(insValue(instruction))
        'L' -> rotateWaypointLeft(insValue(instruction))
        else -> this
    }

    private fun insValue(instruction: String) = instruction.drop(1).toInt()

    // 90 degree clockwise is 2x2 matrix ((0, 1), (-1, 0)) dotted with (dx, dy)
    private fun rotateRight(degrees: Int) = when (degrees) {
        90 -> NavState(x, y, dy, -dx, wx, wy)
        180 -> NavState(x, y, -dx, -dy, wx, wy)
        270 -> NavState(x, y, -dy, dx, wx, wy)
        else -> this
    }

    private fun rotateWaypointRight(degrees: Int) = when (degrees) {
        90 -> NavState(x, y, dx, dy, wy, -wx)
        180 -> NavState(x, y, dx, dy, -wx, -wy)
        270 -> NavState(x, y, dx, dy, -wy, wx)
        else -> this
    }

    private fun rotateLeft(degrees: Int) = rotateRight(360 - degrees)

    private fun rotateWaypointLeft(degrees: Int) = rotateWaypointRight(360 - degrees)

    fun execute(steps: List<String>) = steps.fold(this) { state, next -> state.step(next) }

    fun waypointExecute(steps: List<String>) = steps.fold(this) { state, next -> state.waypointStep(next) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NavState

        if (x != other.x) return false
        if (y != other.y) return false
        if (dx != other.dx) return false
        if (dy != other.dy) return false
        if (wx != other.wx) return false
        if (wy != other.wy) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + dx
        result = 31 * result + dy
        result = 31 * result + wx
        result = 31 * result + wy
        return result
    }

    override fun toString(): String {
        return "NavState(x=$x, y=$y, dx=$dx, dy=$dy, wx=$wx, wy=$wy)"
    }
}