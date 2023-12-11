package Day7

import QuizTaskExec
import runPartQuizAndMeasureTime
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.Comparator

class FirstPart : QuizTaskExec {
    override fun exec(input: List<String>): Int {

        val hands = input.map(::handFromLine)
        val sorted = hands
            .sortedWith(
                Comparator.comparingInt<Hand> { it.type.strength }
                .thenComparing { h -> h.cards }
            )
            .reversed()

        return sorted
            .map { it.bid }
            .zip((sorted.size downTo 1))
            .sumOf { it.first * it.second }
    }
}

class Cards(val values: String): Comparable<Cards> {

    private val charsToStr = mapOf(
        'A' to 15, 'K' to 14, 'Q' to 13, 'J' to 12, 'T' to 11, '9' to 9, '8' to 8, '7' to 7, '6' to 6, '5' to 5, '4' to 4, '3' to 3, '2' to 2
    )
    override fun compareTo(other: Cards): Int {


        val firstDifferentChar = this.values.filterIndexed { index, c -> c != other.values[index] }.first()
        val pos = this.values.indexOf(firstDifferentChar)
        val otherChar = other.values[pos]

        val myScore = charsToStr[firstDifferentChar]!!
        val otherScore = charsToStr[otherChar]!!

//        return if (myScore == otherScore) 0 else
        val res = if (myScore >= otherScore) 1 else -1
        println("Have to compare same Hand type: ${this.values} - ${other.values}, comparison output: $res")
        return res
    }
}
//249644885
data class Hand(
    val cards: Cards,
    val type: HandType,
    val bid: Int
)

fun handFromLine(line: String): Hand {

    val (cards, bid) = line.split(" ")

    return Hand(Cards(cards), extractType(cards), bid.toInt())
}

fun extractType(s: String): HandType {

    fun checkForFiveOfAKind(s: String): Boolean {
        return s.all { it == s.first() }
    }

    fun checkForFourOfAKind(s: String): Boolean {
        val charsCounts = s.associateWith { s.count { c -> c == it } }
        return charsCounts.size == 2 && charsCounts.containsValue(1) && charsCounts.containsValue(4)
    }

    fun checkForHighCard(s: String): Boolean {
        return s.toCharArray().distinct().size == s.length
    }

    fun checkForFullHouse(s: String): Boolean {
        val charsCounts = s.associateWith { s.count { c -> c == it } }
        return charsCounts.size == 2 && charsCounts.containsValue(2) && charsCounts.containsValue(3)
    }

    fun checkForThreeOfAKind(s: String): Boolean {
        val charsCounts = s.associateWith { s.count { c -> c == it } }
        return charsCounts.size == 3 && charsCounts.containsValue(3) && charsCounts.containsValue(1) && charsCounts.containsValue(1)
    }

    fun checkForTwoPair(s: String): Boolean {
        val charsCounts = s.associateWith { s.count { c -> c == it } }
        return charsCounts.size == 3 && charsCounts.containsValue(2) && charsCounts.containsValue(2) && charsCounts.containsValue(1)
    }

    fun checkForOnePair(s: String): Boolean {
        val charsCounts = s.associateWith { s.count { c -> c == it } }
        return charsCounts.size == 4 && charsCounts.containsValue(2)
    }

    if (checkForFiveOfAKind(s)) return HandType.FiveOfAKind
    if (checkForFourOfAKind(s)) return HandType.FourOfAKind
    if (checkForFullHouse(s)) return HandType.FullHouse
    if (checkForThreeOfAKind(s)) return HandType.ThreeOfAKind
    if (checkForTwoPair(s)) return HandType.TwoPair
    if (checkForOnePair(s)) return HandType.OnePair
    if (checkForHighCard(s)) return HandType.HighCard

    throw IllegalArgumentException("Could not find a matching type")
}

enum class HandType(val strength: Int) {
    FiveOfAKind(7),
    FourOfAKind(6),
    FullHouse(5),
    ThreeOfAKind(4),
    TwoPair(3),
    OnePair(2),
    HighCard(1)
}

class SecondPart : QuizTaskExec {
    override fun exec(input: List<String>): Int {
        TODO("Not yet implemented")
    }
}

fun main() {

    val firstPartTestExpected: Int = 6440

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