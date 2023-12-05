package kotlin2023

import readLinesFromFile
import kotlin.math.pow

/**
 * Advent of Code: Day 4
 * https://adventofcode.com/2023/day/4
 *
 * --- Day 4: Scratchcards ---
 */

private fun main() {
    val testInput = readLinesFromFile("2023/test_day04.txt")
    check(getPart1Answer(testInput) == 13)
    check(getPart2Answer(testInput) == 30)

    val fileName = "2023/input_day04.txt"
    val lines = readLinesFromFile(fileName)
    println("Part 1: ${getPart1Answer(lines)}")
    println("Part 2: ${getPart2Answer(lines)}")
}

/**
 * Part 1
 * Determine the number of points a user gets for each line, and return the sum of those points. The number of points
 * is determined by the number of matches between the user's numbers and the winning numbers. The points are calculated
 * by 2^(matches - 1).
 */
private fun getPart1Answer(lines: Sequence<String>): Int {
    return lines.sumOf { line ->
        val (winningNumbers, userNumbers) = line.split("|").map { it.trim().split("\\s+".toRegex()) }
        val matches = userNumbers.count { winningNumbers.contains(it) }
        if (matches > 0) 2f.pow(matches - 1).toInt() else 0
    }
}

/**
 * Part 2
 * Determine the number of scratchcards a user wins. A user wins copies of the next number of scratchcards equal to the
 * number of matches between the user's numbers and the winning numbers. Return the sum of the number of scratchcards
 * the user wins.
 */
private fun getPart2Answer(lines: Sequence<String>): Int {
    val totalCards = (0..<lines.count()).associateWith { 1 }.toMutableMap()
    val matches = lines.map { line ->
        val (winningNumbers, userNumbers) = line.split("|").map { it.trim().split("\\s+".toRegex()) }
        userNumbers.count { winningNumbers.contains(it) }
    }

    matches.forEachIndexed { index, count ->
        val currentCardCount = totalCards[index] ?: 0
        for (offset in 1..count) {
            totalCards.merge(index + offset, currentCardCount, Int::plus)
        }
    }
    return totalCards.values.sum()
}