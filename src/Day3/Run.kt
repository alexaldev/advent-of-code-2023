package Day3

import QuizTaskExec
import runPartQuizAndMeasureTime

class FirstPart : QuizTaskExec {
    override fun exec(input: List<String>): Int {

        input.map { it.filter { c -> !c.isDigit() && c != '.' } }


        val numbers = getNumbersFromLine(input[0])

        return 0
    }

    fun getNumbersFromLine(line: String): List<Pair<Int, Int>> {
        // 467..114..
        val res = mutableListOf<Pair<Int, Int>>()

        line.forEachIndexed { index, c ->

            val accumulator = mutableListOf<Char>()

            if (c.isDigit()) {

                var indx = index
                var next: Char = c
                do {
                    accumulator += next
                    indx += 1
                    next = line[indx]
                } while (next.isDigit())
                res += (accumulator.joinToString("").toInt() to index)
            }
        }

        return res
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