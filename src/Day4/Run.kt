package Day4

import QuizTaskExec
import countLines
import println
import readInput
import runPartQuizAndMeasureTime
import kotlin.math.pow

class FirstPart : QuizTaskExec {
    override fun exec(input: List<String>): Int {

        val whitespace = "\\s+".toRegex()

        return input
            .map { line ->

                line.substringAfter("|").trim().split(whitespace).toSet()
                    .intersect(line.substringBefore("|").trim().substringAfter(":").trim().split(whitespace).toSet())
                    .size
            }
            .filter { it > 0 }
            .sumOf { 2.0.pow(it - 1) }
            .toInt()
    }
}

class SecondPart(
    private val fileLines: Int
) : QuizTaskExec {

    override fun exec(input: List<String>): Int {

        val whitespace = "\\s+".toRegex()

        val cardCopies = (1..fileLines).associateWith { 1 }.toMutableMap()

        input.forEachIndexed { index, line ->

            val winningMatchesCount = line.substringAfter("|").trim().split(whitespace).toSet()
                .intersect(line.substringBefore("|").trim().substringAfter(":").trim().split(whitespace).toSet())
                .size

            (index + 2..(index + winningMatchesCount + 1)).forEach {
                cardCopies.replace(it, cardCopies[it]!! + cardCopies[index + 1]!!)
            }
        }

        return cardCopies.values.sum()
    }
}

fun main() {

    val firstPartTestExpected: Int = 13

    val firstPart: QuizTaskExec = FirstPart()
    val thisDayId = firstPart.javaClass.packageName.substringAfter("Day")

    val p1Duration = runPartQuizAndMeasureTime(firstPart, firstPartTestExpected, thisDayId)

    println("First part duration: ${p1Duration.inWholeMilliseconds}")


    val secondPart: QuizTaskExec = SecondPart(countLines("Day4/input").toInt())
    val input = readInput("Day4/input")

    secondPart.exec(input).println()
//
//    try {
//
//        val secondPartTestExpected: Int = 30
//        val p2Duration = runPartQuizAndMeasureTime(secondPart, secondPartTestExpected, thisDayId)
//        println("Second part duration: ${p2Duration.inWholeMilliseconds}")
//
//    } catch (_: Exception) {
//        println("Part2 not yet implemented")
//    }
}