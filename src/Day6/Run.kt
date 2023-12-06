package Day6

import QuizTaskExec
import runPartQuizAndMeasureTime

class FirstPart(
    private val durationsToRecords: List<Pair<Int, Int>>
) : QuizTaskExec {

    override fun exec(input: List<String>): Int {

        return durationsToRecords.map { durAndRec ->

            val (raceDuration, record) = durAndRec

            (0..raceDuration)
                .map { it * (raceDuration - it) }
                .count { it > record }

        }.fold(1) { acc: Int, i: Int -> acc * i }
    }
}

class SecondPart(
    private val durationToRecord: Pair<Long, Long>
) : QuizTaskExec {
    override fun exec(input: List<String>): Int {

        val (raceDuration, record) = durationToRecord

        return (0..raceDuration)
            .asSequence()
            .map { it * (raceDuration - it) }
            .count { it > record }
    }
}

fun main() {

    val firstPartTestExpected: Int = 288

    val firstPartTestInp = listOf(7 to 9, 15 to 40, 30 to 200)

    val firstPart: QuizTaskExec = FirstPart(firstPartTestInp)

    val thisDayId = firstPart.javaClass.packageName.substringAfter("Day")

    val p1Duration = runPartQuizAndMeasureTime(firstPart, firstPartTestExpected, thisDayId)

    println("First part duration: ${p1Duration.inWholeMilliseconds}")

    try {

        val secondPartTestExpected: Int = 41382569
        val secondPartInput = 62649190L to 553101014731074

        val secondPart: QuizTaskExec = SecondPart(62649190L to 553101014731074)
        val p2Duration = runPartQuizAndMeasureTime(secondPart, secondPartTestExpected, thisDayId)
        println("Second part duration: ${p2Duration.inWholeMilliseconds}")

    } catch (_: Exception) {
    }
}