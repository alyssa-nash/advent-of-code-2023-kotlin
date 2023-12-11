package kotlin2023

import readLinesFromFile

/**
 * Advent of Code: Day 9
 * https://adventofcode.com/2023/day/9
 *
 * --- Day 9: Mirage Maintenance ---
 */

private fun main() {
    val testInput = readLinesFromFile("2023/test_day09.txt")
    check(getPart1Answer(testInput) == 114)
    check(getPart2Answer(testInput) == 2)

    val fileName = "2023/input_day09.txt"
    val lines = readLinesFromFile(fileName)
    println("Part 1: ${getPart1Answer(lines)}")
    println("Part 2: ${getPart2Answer(lines)}")
}

/**
 * Part 1
 */
private fun getPart1Answer(lines: Sequence<String>): Int {
    return lines.sumOf { line ->
        val history = line.split(" ").map { it.toInt() }
        var nextSequence = history.toList()
        val finalSequence = mutableListOf<Int>()
        while(nextSequence.any { it != 0 }) {
            finalSequence.add(nextSequence.last())
            val differences = nextSequence.zipWithNext { a, b -> b - a }
            nextSequence = differences.toList()
        }
        finalSequence.sum()
    }
}

/**
 * Part 2
 */
private fun getPart2Answer(lines: Sequence<String>): Int {
    return lines.sumOf { line ->
        val history = line.split(" ").map { it.toInt() }
        var nextSequence = history.toList()
        val finalSequence = mutableListOf(nextSequence.first())
        while(nextSequence.any { it != 0 }) {
            val differences = nextSequence.zipWithNext { a, b -> b - a }
            nextSequence = differences.toList()
            finalSequence.add(nextSequence.first())
        }
        val result = finalSequence.toList().reversed().fold(0) { acc, i -> i - acc }
        result
    }
}