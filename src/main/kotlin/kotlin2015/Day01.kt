package kotlin2015

import readLinesFromFile

/**
 * Advent of Code: Day 1
 * https://adventofcode.com/2015/day/1
 */

fun main() {
    val fileName = "2015/input_day01.txt"
    val lines = readLinesFromFile(fileName)
    println("Part 1: ${getPart1Answer(lines)}")
    println("Part2: ${getPart2Answer(lines)}")
}

private fun getPart1Answer(lines: Sequence<String>): Int {
    return lines.joinToString().fold(0) { currentFloor, direction ->
        when (direction) {
            '(' -> currentFloor + 1
            ')' -> currentFloor - 1
            else -> currentFloor
        }
    }
}

private fun getPart2Answer(lines: Sequence<String>): Int {
    return lines.joinToString().foldIndexed(0) { index, currentFloor, direction ->
        val nextFloor = when (direction) {
            '(' -> currentFloor + 1
            ')' -> currentFloor - 1
            else -> currentFloor
        }
        if (nextFloor < 0) {
            return index + 1
        } else nextFloor
    }
}