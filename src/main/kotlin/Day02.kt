/**
 * Advent of Code: Day 2
 * https://adventofcode.com/2023/day/2
 *
 * --- Day 2: Cube Conundrum ---
 * Goal: Consume a file that contains lines of text. Each line contains a game result. Output the "sum" of the
 * game result based on the criteria in each part.
 */

private fun main() {
    val testInput = readLinesFromFile("test_day02.txt")
    check(getPart1Answer(testInput) == 8)
    check(getPart2Answer(testInput) == 2286)

    val fileName = "input_day02.txt"
    val lines = readLinesFromFile(fileName)
    println("Part 1: ${getPart1Answer(lines)}")
    println("Part 2: ${getPart2Answer(lines)}")
}

val idPattern = Regex("Game (\\d+):")
val pattern = Regex("\\d+ (blue|red|green)")
val colorCounts = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14
)

/**
 * Part 1
 * Determine if a game is valid by comparing the number of each color in the game to the number of each color allowed.
 * Return the sum of the ids of all valid games.
 */
private fun getPart1Answer(lines: Sequence<String>): Int {
    return lines.sumOf { line ->
        val idMatch: MatchResult? = idPattern.find(line)
        val id = idMatch?.groupValues?.get(1)?.toInt() ?: 0
        if (validateGame(line)) id else 0
    }
}

private fun validateGame(line: String): Boolean {
    return pattern.findAll(line).all { match ->
        val (number, color) = match.value.split(" ")
        number.toInt() <= colorCounts.getOrDefault(color, 0)
    }
}

/**
 * Part 2
 * Determine the power of a game by multiplying the largest number of each color in the game.
 * Return the sum of the power of all games.
 */
private fun getPart2Answer(lines: Sequence<String>): Int {
    return lines.sumOf { line ->
        getPowerOfGame(line)
    }
}

private fun getPowerOfGame(line: String): Int {
    val largestNumberPerColor = mutableMapOf(
        "red" to 0,
        "green" to 0,
        "blue" to 0
    )
    pattern.findAll(line).forEach { match ->
        val (number, color) = match.value.split(" ")
        largestNumberPerColor[color] = maxOf(largestNumberPerColor[color] ?: 0, number.toInt())
    }
    return largestNumberPerColor.values.reduce { acc, i -> acc * i }
}