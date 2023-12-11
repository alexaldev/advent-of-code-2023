package Day8

import QuizTaskExec
import runPartQuizAndMeasureTime
import java.lang.IllegalStateException

class FirstPart : QuizTaskExec {
    override fun exec(input: List<String>): Int {

        val instructions = input.first().toCharArray()
        val nodesToDestinations = hashMapOf<String, Pair<String, String>>()

        input.subList(2, input.size).map {
            val spls = it.split("=")
            val nodeStart = spls.first().trim()
            val nodeDest = spls[1].trim().split(",")
            val leftNodeDest = nodeDest.first().removePrefix("(").trim()
            val rightNodeDest = nodeDest[1].removeSuffix(")").trim()

            nodesToDestinations[nodeStart] = leftNodeDest to rightNodeDest
        }

        var count = 0
        var currentNode = "AAA" // Start
        var found = false
        var currentInstructionIndex = 0
        while(!found) {

            val currentInstruction = instructions[currentInstructionIndex]

            currentNode = when(currentInstruction) {
                'R' -> {
                    nodesToDestinations[currentNode]!!.second
                }
                'L' -> {
                    nodesToDestinations[currentNode]!!.first
                }
                else -> throw IllegalStateException("Invalid instruction $currentInstruction")
            }

            count += 1

            currentInstructionIndex = (currentInstructionIndex + 1) % instructions.size
            if (currentNode == "ZZZ") {
                found = true
            }
        }

        return count
    }
}

class SecondPart : QuizTaskExec {
    override fun exec(input: List<String>): Int {

        val instructions = input.first().toCharArray()
        val nodesToDestinations = hashMapOf<String, Pair<String, String>>()

        input.subList(2, input.size).map {
            val spls = it.split("=")
            val nodeStart = spls.first().trim()
            val nodeDest = spls[1].trim().split(",")
            val leftNodeDest = nodeDest.first().removePrefix("(").trim()
            val rightNodeDest = nodeDest[1].removeSuffix(")").trim()

            nodesToDestinations[nodeStart] = leftNodeDest to rightNodeDest
        }

        val allStartingNodes = nodesToDestinations.filterKeys { it.endsWith("A") }

        var count = 0
        var allEndAtZ = false
        var currentInstructionIndex = 0
        val currentNodesKeys = mutableSetOf<String>().apply { addAll(allStartingNodes.keys) }

        while(!allEndAtZ) {

            val currentInstruction = instructions[currentInstructionIndex]

            val toAdd = when(currentInstruction) {
                'R' -> {
                    currentNodesKeys.map { nodesToDestinations[it]!!.second }
                }
                'L' -> {
                    currentNodesKeys.map { nodesToDestinations[it]!!.first }
                }
                else -> throw IllegalStateException("Invalid instruction $currentInstruction")
            }

            currentNodesKeys.clear()
            currentNodesKeys.addAll(toAdd)

            count += 1

            currentInstructionIndex = (currentInstructionIndex + 1) % instructions.size
            if (currentNodesKeys.all { it.endsWith("Z") }) {
                allEndAtZ = true
            }
        }

        return count
    }
}

fun main() {

    val firstPartTestExpected: Int = 6

    val firstPart: QuizTaskExec = FirstPart()

    val thisDayId = firstPart.javaClass.packageName.substringAfter("Day")

//    val p1Duration = runPartQuizAndMeasureTime(firstPart, firstPartTestExpected, thisDayId)

//    println("First part duration: ${p1Duration.inWholeMilliseconds}")

    try {

        val secondPart: QuizTaskExec = SecondPart()
        val secondPartTestExpected: Int = 6

        val p2Duration = runPartQuizAndMeasureTime(secondPart, secondPartTestExpected, thisDayId)
        println("Second part duration: ${p2Duration.inWholeMilliseconds}")

    } catch (_: Exception) {
    }
}