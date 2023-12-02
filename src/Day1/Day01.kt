package Day1

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { "${it.first { c->  c.isDigit() }}${it.last { c -> c.isDigit() }}".toInt() }
    }

    fun part2(input: List<String>): Int {

            return input.sumOf { line ->

                val intsAsStrs = intsToValues
                    .keys
                    .filter { s -> line.contains(s) }
                    .sortedBy { line.indexOf(it) }

                var repLine = line

                intsAsStrs.forEach {
                    repLine = repLine.replace(it, intsToValues[it]!!.toString())
                }

                "${repLine.first { it.isDigit() }}${repLine.last { it.isDigit() }}".toInt()
            }
    }

    part2(listOf("sevenine"))

    val testInput = readInput("Day1/Day01_test")
//    check(part2(testInput) == 281)

    val input = readInput("Day1/Day01")
//    part1(input).println()
//    part2(input).println()
}

val intsToValues = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)
