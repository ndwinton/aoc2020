package advent2020

fun day01(vararg expenses: Int): Int =
    expenses.filterIndexed { index, current ->
        expenses.drop(index + 1).contains(2020 - current)
    }.map { it * (2020 - it)}.first()
