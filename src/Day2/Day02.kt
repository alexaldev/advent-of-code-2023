package Day2

import println
import readInput

fun main() {

    fun part1(input: List<String>): Int {

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
                    when(cube.coloredCube) {
                        ColoredCube.red -> {
                            if (cube.count > 12) resIds -= gameId
                        }
                        ColoredCube.green -> {
                            if (cube.count > 13) resIds -= gameId
                        }
                        ColoredCube.blue -> {
                            if (cube.count > 14) resIds -= gameId
                        }
                    }
                }
            }

        }

        return resIds.sumOf { it }
    }

    fun part2(input: List<String>): Int {

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

                    when(cube.coloredCube) {
                        ColoredCube.red -> {
                            if (cube.count > maxRed) maxRed = cube.count
                        }
                        ColoredCube.green -> {
                            if (cube.count > maxGreen) maxGreen = cube.count
                        }
                        ColoredCube.blue -> {
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

    val testInput = readInput("Day2/Day02_test")
    check(part2(testInput) == 2286)

    val input = readInput("Day2/Day02")
    part1(input).println()
    part2(input).println()
}

fun sToCube(s: String): Cube {
    // 3 blue
    val spit = s.trim().split(" ")
    return Cube(ColoredCube.valueOf(spit[1]), spit.first().toInt())
}

class Cube(
    val coloredCube: ColoredCube,
    val count: Int
)

enum class ColoredCube {
    red, green, blue
}