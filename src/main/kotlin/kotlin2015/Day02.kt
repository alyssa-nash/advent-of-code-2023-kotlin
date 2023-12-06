package kotlin2015

import readLinesFromFile

/**
 * Advent of Code: Day 2
 * https://adventofcode.com/2015/day/2
 */

fun main() {
    val fileName = "2015/input_day02.txt"
    val lines = readLinesFromFile(fileName)
    println("Part 1: ${getPart1Answer(lines)}")
    println("Part2: ${getPart2Answer(lines)}")
}

private fun getPart1Answer(lines: Sequence<String>): Int {
    return lines.sumOf { line ->
        val (l, w, h) = line.split("x").map { it.toInt() }
        val sides = listOf(l * w, w * h, h * l)
        // 2*l*w + 2*w*h + 2*h*l + smallest side
        2 * sides.sum() + sides.min()
    }
}

private fun getPart2Answer(lines: Sequence<String>): Int {
    return lines.sumOf { line ->
        val (l, w, h) = line.split("x").map { it.toInt() }
        val sides = listOf(l, w, h)
        // Add all sides * 2, subtract the largest side * 2, and add the volume
        2 * sides.sum() - 2 * sides.max() + sides.reduce { acc, i -> acc * i }
    }
}