package day_one

import java.io.File

/**
 * Advent of Code: Day 1
 * https://adventofcode.com/2023/day/1
 *
 * --- Day 1: Trebuchet?! ---
 * Goal: Consume a file that contains lines of text. Each line contains a number of characters. Output the "sum" of the
 * found digits based on the criteria in each part.
 */

fun main() {
    val fileName = "input_day1.txt"
    try {
        val sum1 = File(ClassLoader.getSystemResource(fileName).file).useLines { lines ->
            getCalibrationFromInputPart1(lines)
        }
        println("Total Sum - Part 1: $sum1")

        val sum2 = File(ClassLoader.getSystemResource(fileName).file).useLines { lines ->
            getCalibrationFromInputPart2(lines)
        }
        println("Total Sum - Part 2: $sum2")
    } catch (e: Exception) {
        println("Error reading file: ${e.message}")
    }
}

/**
 * Part 1
 * Find the first digit and the last digit in each line, and concatenate them together to form a two-digit number.
 * Return the sum of all of these two-digit numbers.
 */
fun getCalibrationFromInputPart1(lines: Sequence<String>): Int {
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
fun getCalibrationFromInputPart2(lines: Sequence<String>): Int {
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