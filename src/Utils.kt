import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import java.lang.Exception
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.outputStream
import kotlin.io.path.readLines
import kotlin.time.Duration
import kotlin.time.measureTime


interface QuizTaskExec {
    fun exec(input: List<String>): Int
}

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

fun countLines(name: String) = Path("src/$name.txt").readLines().stream().count()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun Any?.copyToClipboard() {
    try {
        val stringSelection = StringSelection(this.toString())
        val clipboard: Clipboard = Toolkit.getDefaultToolkit().getSystemClipboard()
        clipboard.setContents(stringSelection, null)
    } catch (_ : Exception) {}
}

fun runPartQuizAndMeasureTime(quizPartExec: QuizTaskExec, testExpectedResult: Int, dayPackage: String): Duration {

    return measureTime {

        val test = readInput("Day${dayPackage}/input_test")
        val testResult = quizPartExec.exec(test)
        check(testExpectedResult == testResult) { "Expected $testExpectedResult but was $testResult" }

        quizPartExec
            .exec(readInput("Day${dayPackage}/input"))
            .apply {
                this.println()
                this.copyToClipboard()
            }
    }
}