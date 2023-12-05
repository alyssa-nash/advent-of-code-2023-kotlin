/**
 * Advent of Code: Day 5
 * https://adventofcode.com/2023/day/5
 *
 * --- Day 5: If You Give A Seed A Fertilizer ---
 */

private fun main() {
    val testInput = readLinesFromFile("test_day05.txt")
    check(getPart1Answer(testInput) == 35.toLong())
    check(getPart2Answer(testInput) == 46.toLong())

    val fileName = "input_day05.txt"
    val lines = readLinesFromFile(fileName)
    println("Part 1: ${getPart1Answer(lines)}")
    println("Part 2: ${getPart2Answer(lines)}")
}

/**
 * Part 1
 */

data class MapSet(val source: LongRange, val destination: LongRange)
data class ConversionType(val list: List<MapSet>)
private fun getPart1Answer(lines: Sequence<String>): Long {
    val seeds = lines.first().split(" ").drop(1).map { it.toLong() }
    val conversionMaps = getConversionMaps(lines)

    return seeds.minOf { seed ->
        conversionMaps.fold(seed) { currentSeed, map ->
            map.list.firstOrNull { currentSeed in it.source }?.let {
                it.destination.first + (currentSeed - it.source.first)
            } ?: currentSeed
        }
    }
}

/**
 * Part 2
 */
private fun getPart2Answer(lines: Sequence<String>): Long {
    val seeds = lines.first().split(" ").drop(1).map { it.toLong() }
    val seedRanges = seeds.chunked(2) { (seed, range) -> seed..< seed + range }
    val conversionMaps = getConversionMaps(lines)

    for (location in 1..< Long.MAX_VALUE) {
        val test = conversionMaps.reversed().fold(location) { currentLocation, map ->
            map.list.firstOrNull { currentLocation in it.destination }?.let {
                it.source.first + (currentLocation - it.destination.first)
            } ?: currentLocation
        }
        if (seedRanges.any { range -> test in range }) {
            return location
        }
    }
    return 0
}

private fun getConversionMaps(lines: Sequence<String>): List<ConversionType> =
    lines.filter { ':' !in it }
        .joinToString("\n")
        .trim()
        .split("\n\n")
        .map { section ->
            ConversionType(
                section.lines().map { mapSet ->
                    mapSet.split(" ").map { it.toLong() }.let { (destination, source, range) ->
                        MapSet(source..(source + range), destination..(destination + range))
                    }
                }
            )
        }