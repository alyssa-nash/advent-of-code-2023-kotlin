package kotlin2023

import readLinesFromFile
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

/**
 * Advent of Code: Day 6
 * https://adventofcode.com/2023/day/6
 *
 * --- Day 6: Wait For It ---
 */

private fun main() {
    val testInput = readLinesFromFile("2023/test_day06.txt")
    check(getPart1Answer(testInput) == 288)
    check(getPart2Answer(testInput) == 71503)

    val fileName = "2023/input_day06.txt"
    val lines = readLinesFromFile(fileName)
    println("Part 1: ${getPart1Answer(lines)}")
    println("Part 2: ${getPart2Answer(lines)}")
}

/**
 * Part 1
 */
val spacePattern = Regex("\\s+")
private fun getPart1Answer(lines: Sequence<String>): Int {
    val numbersLine1 = lines.first()
        .split(spacePattern)
        .mapNotNull { it.toDoubleOrNull() }
    val numbersLine2 = lines.last()
        .split(spacePattern)
        .mapNotNull { it.toDoubleOrNull() }
    val pairedNumbers = numbersLine1.zip(numbersLine2)

    return pairedNumbers.map { race ->
        getPossibleWins(race)
    }.reduce(Int::times)
}

/**
 * I'll admit, I walked through the formula rearrangements in Wolfram Alpha. I came up with the original formula and
 * asked for the formula for the bounds.
 *
 * buttonPressed * (raceLength - buttonPressed) = distanceTraveled >= distanceToBeat
 * 0 >= buttonPressed^2 - buttonPressed * raceLength + distanceToBeat
 * bounds = (raceLength +- sqrt(raceLength^2 - 4 * distanceToBeat)) / 2
 * numberOfPossibleWins = floor(highRoot) - ceil(lowRoot) + 1
 */
private fun getPossibleWins(race: Pair<Double, Double>) : Int {
    val raceLength = race.first
    val timeToBeat = race.second + 1
    val highBounds = (raceLength + sqrt((raceLength * raceLength) - (4 * timeToBeat))) / 2.0
    val lowBounds = (raceLength - sqrt((raceLength * raceLength) - (4 * timeToBeat))) / 2.0
    return (floor(highBounds) - ceil(lowBounds) + 1).toInt()
}

/**
 * Part 2
 */
private fun getPart2Answer(lines: Sequence<String>): Int {
    val numbersLine1 = lines.first()
        .split(spacePattern)
        .mapNotNull { it.toIntOrNull() }
        .joinToString("")
        .toDouble()
    val numbersLine2 = lines.last()
        .split(spacePattern)
        .mapNotNull { it.toIntOrNull() }
        .joinToString("")
        .toDouble()
    val race = Pair(numbersLine1, numbersLine2)

    return getPossibleWins(race)
}