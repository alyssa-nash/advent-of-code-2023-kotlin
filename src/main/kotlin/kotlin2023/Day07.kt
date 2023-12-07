package kotlin2023

import readLinesFromFile

/**
 * Advent of Code: Day 7
 * https://adventofcode.com/2023/day/7
 *
 * --- Day 7: Camel Cards ---
 */

private fun main() {
    val testInput = readLinesFromFile("2023/test_day07.txt")
    check(getPart1Answer(testInput) == 6440)
    check(getPart2Answer(testInput) == 5905)

    val fileName = "2023/input_day07.txt"
    val lines = readLinesFromFile(fileName)
    println("Part 1: ${getPart1Answer(lines)}")
    println("Part 2: ${getPart2Answer(lines)}")
}

/**
 * Part 1
 */
private data class Hand(val cards: String, val groups: Int, val bid: Int)
private fun getPart1Answer(lines: Sequence<String>): Int {
    return evaluateHands(lines, "ABCDE")
}

private fun evaluateHands(lines: Sequence<String>, faces: String) : Int {
    return lines.map { line ->
        val (hand, bid) =  line.split(" ")
        val modifiedHand = hand.map { faces.getOrNull("TJQKA".indexOf(it)) ?: it }.joinToString("")
        val best = modifiedHand.maxOfOrNull { char -> type(modifiedHand.replace('0', char)) } ?: 0
        Hand(modifiedHand, best, bid.toInt())
    }.sortedWith(compareBy({ it.groups }, { it.cards }))
        .mapIndexed { index, hand -> (index + 1) * hand.bid }
        .sum()
}
fun type(hand: String): Int = hand.sumOf { char -> hand.count { it == char } }

/**
 * Part 2
 */
private fun getPart2Answer(lines: Sequence<String>): Int {
    return evaluateHands(lines, "A0CDE")
}