package day_one

import java.io.File

/**
 * Advent of Code: Day 1
 * https://adventofcode.com/2023/day/1
 *
 * --- Day 1: Trebuchet?! ---
 * Goal: Consume a file that contains lines of text. Each line contains a number of characters, some of which are digits.
 * Find the first digit and the last digit in each line, and concatenate them together to form a two-digit number.
 * Return the sum of all of these two-digit numbers.
 */

fun main() {
    val fileName = "input"
    try {
        val sum = File(ClassLoader.getSystemResource(fileName).file).useLines { lines ->
            getCalibrationFromInput(lines)
        }
        println("Total Sum: $sum")
    } catch (e: Exception) {
        println("Error reading file: ${e.message}")
    }
}

fun getCalibrationFromInput(lines: Sequence<String>): Int {
    return lines.sumOf { line ->
        val digits = line.filter { it.isDigit() }
        val firstDigit = digits.firstOrNull() ?: "0"
        val secondDigit = digits.lastOrNull() ?: firstDigit
        val pairValue = "$firstDigit$secondDigit".toInt()
        pairValue
    }
}