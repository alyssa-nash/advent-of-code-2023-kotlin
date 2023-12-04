/**
 * Advent of Code: Day 3
 * https://adventofcode.com/2023/day/3
 *
 * --- Day 2: Gear Ratios ---
 * Goal: Consume a file that contains lines of text. Each line contains a mix of numbers, symbols, and periods. Output
 * the "sum" of the gears based on the criteria in each part.
 */

private fun main() {
    val testInput = readLinesFromFile("test_day03.txt")
    check(getPart1Answer(testInput) == 4361)
    check(getPart2Answer(testInput) == 467835)

    val fileName = "input_day03.txt"
    val lines = readLinesFromFile(fileName)
    println("Part 1: ${getPart1Answer(lines)}")
    println("Part 2: ${getPart2Answer(lines)}")
}

private fun extractParts(lines: Sequence<String>): Map<Pair<Int, Int>, Int> =
    lines.flatMapIndexed { lineNumber, line ->
        Regex("\\d+").findAll(line).map { match ->
            Pair(match.range.first, lineNumber) to match.value.toInt()
        }
    }.toMap()

private fun extractSymbols(lines: Sequence<String>, pattern: Regex): Sequence<Pair<Int, Int>> =
    lines.flatMapIndexed { lineNumber, line ->
        pattern.findAll(line).map { match ->
            Pair(match.range.first, lineNumber)
        }
    }

/**
 * Get the coordinates of the adjacent parts of a given coordinate by adding and subtracting 1 from the x and y values.
 */
private fun getAdjacentCoordinates(coordinate: Pair<Int, Int>, length: Int): Set<Pair<Int, Int>> {
    val (x, y) = coordinate
    return (x - 1..x + length).flatMap { i ->
        (y - 1..y + 1).map { j ->
            if (i in x..<x + length && j == y) null else Pair(i, j)
        }
    }.filterNotNull().toSet()
}

/**
 * Part 1
 * Find the numbers that are adjacent to a symbol, and return the sum of those numbers.
 */
private fun getPart1Answer(lines: Sequence<String>): Int {
    val symbolPattern = Regex("[!@#\$%^&*()+\\-=_/<>,`~|]")
    val parts = extractParts(lines)
    val symbols = extractSymbols(lines, symbolPattern)

    return parts.filter { (coordinate, value) ->
        getAdjacentCoordinates(coordinate, value.toString().length)
            .any(symbols::contains)
    }.values.sum()
}

/**
 * Part 2
 * Look for every asterisk in the input, and find the two numbers that are adjacent to it. Multiply those two numbers
 * together, and return the sum of all of those products.
 */
private fun getPart2Answer(lines: Sequence<String>): Int {
    val asteriskPattern = Regex("\\*")
    val parts = extractParts(lines)
    val asterisks = extractSymbols(lines, asteriskPattern)

    return asterisks.mapNotNull { asterisk ->
        // Find all parts that are adjacent to an asterisk
        parts.filter { (coordinates, _) ->
            getAdjacentCoordinates(coordinates, parts[coordinates].toString().length).contains(asterisk)
        }.values
            // Only keep the parts if there are exactly two adjacent parts
            .takeIf { it.size == 2 }
            // Multiply the two adjacent parts together
            ?.reduce(Int::times)
    }.sum()
}