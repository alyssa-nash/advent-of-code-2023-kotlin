/**
 * Advent of Code: Day 1
 * https://adventofcode.com/2023/day/1
 *
 * --- Day 1: Trebuchet?! ---
 * Goal: Consume a file that contains lines of text. Each line contains a number of characters. Output the "sum" of the
 * found digits based on the criteria in each part.
 */

fun main() {
    val testInput = readLinesFromFile("test_day01_part1.txt")
    check(getPart1Answer(testInput) == 142)
    val testInput2 = readLinesFromFile("test_day01_part2.txt")
    check(getPart2Answer(testInput2) == 281)

    val fileName = "input_day01.txt"
    val lines = readLinesFromFile(fileName)
    println("Part 1: ${getPart1Answer(lines)}")
    println("Part2: ${getPart2Answer(lines)}")
}

/**
 * Part 1
 * Find the first digit and the last digit in each line, and concatenate them together to form a two-digit number.
 * Return the sum of all of these two-digit numbers.
 */
private fun getPart1Answer(lines: Sequence<String>): Int {
    return lines.sumOf { line ->
        val digits = line.filter { it.isDigit() }
        val firstDigit = digits.firstOrNull() ?: "0"
        val secondDigit = digits.lastOrNull() ?: firstDigit
        val pairValue = "$firstDigit$secondDigit".toInt()
        pairValue
    }
}

/**
 * Part 2
 * Find the first and last number in each line, either as a digit or its word equivalent, and concatenate them together
 * to form a two-digit number. Return the sum of all of these two-digit numbers.
 */
private fun getPart2Answer(lines: Sequence<String>): Int {
    val numberDictionary: Map<String, Int> = mapOf(
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

    return lines.sumOf { line ->
        val firstItem: String = line.asSequence()
            .mapIndexedNotNull { index, char ->
                when {
                    char.isDigit() -> char.toString()
                    else -> numberDictionary.entries
                        .firstOrNull { line.startsWith(it.key, index) }
                        ?.value
                        ?.toString()
                }
            }
            .first()


        val lastItem: String = line.reversed().asSequence()
            .mapIndexedNotNull { reverseIndex, char ->
                val index = line.lastIndex - reverseIndex
                when {
                    char.isDigit() -> char.toString()
                    else -> numberDictionary.entries
                        .firstOrNull { entry ->
                            val word = entry.key
                            val wordEndIndex = index + 1
                            val wordStartIndex = wordEndIndex - word.length
                            wordStartIndex >= 0 && line.startsWith(word, wordStartIndex)
                        }
                        ?.value
                        ?.toString()
                }
            }
            .first()

        val pairValue = "$firstItem$lastItem".toInt()
        pairValue
    }
}