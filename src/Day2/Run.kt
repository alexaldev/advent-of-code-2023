package Day2

import QuizTaskExec
import runPartQuizAndMeasureTime
import java.lang.Exception

fun main() {

    val firstPartTestExpected: Int = 8

    val firstPart: QuizTaskExec = FirstPart()
    val secondPart: QuizTaskExec = SecondPart()
    val thisDayId = firstPart.javaClass.packageName.substringAfter("Day")

    val p1Duration = runPartQuizAndMeasureTime(firstPart, firstPartTestExpected, thisDayId)

    println("First part duration: ${p1Duration.inWholeMilliseconds}")

    val secondPartTestExpected: Int = 2286

    try {

        val p2Duration = runPartQuizAndMeasureTime(secondPart, secondPartTestExpected, thisDayId)
        println("Second part duration: ${p2Duration.inWholeMilliseconds}")

    } catch(_ : Exception) {}
}

class FirstPart : QuizTaskExec {
    override fun exec(input: List<String>): Int {
        val resIds = mutableSetOf<Int>()

        input.map {

            val idAndRest = it.split(":")
            val gameId = it.split(":").first().split(" ")[1].toInt()
            val rest = idAndRest[1].trim()

            resIds += gameId

            rest.split(";").forEach { group ->

                val cubes = group.split(",")
                    .map { sToCube(it) }

                cubes.forEach { cube ->
                    when(cube.color) {
                        CubeColor.red -> {
                            if (cube.count > 12) resIds -= gameId
                        }
                        CubeColor.green -> {
                            if (cube.count > 13) resIds -= gameId
                        }
                        CubeColor.blue -> {
                            if (cube.count > 14) resIds -= gameId
                        }
                    }
                }
            }
        }

        return resIds.sumOf { it }
    }
}

class SecondPart : QuizTaskExec {
    override fun exec(input: List<String>): Int {
        var totalPowers = 0

        input.map {

            val idAndRest = it.split(":")
            val rest = idAndRest[1].trim()

            var maxRed = Integer.MIN_VALUE
            var maxBlue = Integer.MIN_VALUE
            var maxGreen = Integer.MIN_VALUE

            rest.split(";").forEach { group ->


                val cubes = group.split(",")
                    .map { sToCube(it) }

                cubes.forEach { cube ->

                    when(cube.color) {
                        CubeColor.red -> {
                            if (cube.count > maxRed) maxRed = cube.count
                        }
                        CubeColor.green -> {
                            if (cube.count > maxGreen) maxGreen = cube.count
                        }
                        CubeColor.blue -> {
                            if (cube.count > maxBlue) maxBlue = cube.count
                        }
                    }
                }
            }

            val groupPower = maxRed * maxBlue * maxGreen
            totalPowers += groupPower
        }

        return totalPowers
    }
}

fun sToCube(s: String): Cube {
    // 3 blue
    val spit = s.trim().split(" ")
    return Cube(CubeColor.valueOf(spit[1]), spit.first().toInt())
}

class Cube(
    val color: CubeColor,
    val count: Int
)

enum class CubeColor {
    red, green, blue
}
