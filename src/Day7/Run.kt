package Day7

import QuizTaskExec
import runPartQuizAndMeasureTime

class FirstPart : QuizTaskExec {
    override fun exec(input: List<String>): Int {
        TODO("Not yet implemented")
    }
}

class SecondPart : QuizTaskExec {
    override fun exec(input: List<String>): Int {
        TODO("Not yet implemented")
    }
}

fun main() {

    val firstPartTestExpected: Int = 100

    val firstPart: QuizTaskExec = FirstPart()
    val secondPart: QuizTaskExec = SecondPart()
    val thisDayId = firstPart.javaClass.packageName.substringAfter("Day")

    val p1Duration = runPartQuizAndMeasureTime(firstPart, firstPartTestExpected, thisDayId)

    println("First part duration: ${p1Duration.inWholeMilliseconds}")

    val secondPartTestExpected: Int = TODO()

    try {

        val p2Duration = runPartQuizAndMeasureTime(secondPart, secondPartTestExpected, thisDayId)
        println("Second part duration: ${p2Duration.inWholeMilliseconds}")

    } catch (_: Exception) {
    }
}