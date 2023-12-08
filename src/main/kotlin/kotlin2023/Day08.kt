package kotlin2023

import readLinesFromFile

/**
 * Advent of Code: Day 8
 * https://adventofcode.com/2023/day/8
 *
 * --- Day 8: Haunted Wasteland ---
 */

private fun main() {
    val testInput = readLinesFromFile("2023/test_day08_example1.txt")
    val testInput2 = readLinesFromFile("2023/test_day08_example2.txt")
    val testInput3 = readLinesFromFile("2023/test_day08_part2.txt")
    check(getPart1Answer(testInput) == 2)
    check(getPart1Answer(testInput2) == 6)
    check(getPart2Answer(testInput3) == 6L)

    val fileName = "2023/input_day08.txt"
    val lines = readLinesFromFile(fileName)
    println("Part 1: ${getPart1Answer(lines)}")
    println("Part 2: ${getPart2Answer(lines)}")
}

/**
 * Part 1
 */
private fun getPart1Answer(lines: Sequence<String>): Int {
    val directions = lines.first().toList()
    val routeMap = parseMap(lines)
    return countSteps("AAA", routeMap, directions)
}

private fun parseMap(lines: Sequence<String>): Map<String, Pair<String, String>> {
    val regex = """(\w{3}) = \((\w{3}), (\w{3})\)""".toRegex()
    return lines.drop(2).associate {  line ->
        val matchResult = regex.matchEntire(line)
        val (key, value1, value2) = matchResult!!.destructured
        key to Pair(value1, value2)
    }
}

private fun countSteps (startPosition: String, map: Map<String, Pair<String, String>>, directions: List<Char>): Int {
    var currentPosition = startPosition
    var stepsTaken = 0
    val infiniteSequence = generateSequence { directions }.flatten().iterator()
    while (infiniteSequence.hasNext() && !currentPosition.endsWith("Z")) {
        val direction = infiniteSequence.next()
        val (left, right) = map.getValue(currentPosition)
        currentPosition = if (direction == 'R') right else left
        stepsTaken++
    }

    return stepsTaken
}

/**
 * Part 2
 */
private fun getPart2Answer(lines: Sequence<String>): Long {
    val directions = lines.first().toList()
    val map = parseMap(lines)

    return map.keys.filter { it.endsWith("A") }
        .map {
            countSteps(it, map, directions).toLong()
        }
        .reduce (::lcm)
}

private fun gcd(a: Long, b: Long): Long {
    if (b == 0L) return a
    return gcd(b, a % b)
}

private fun lcm(a: Long, b: Long): Long {
    return a / gcd(a, b) * b
}