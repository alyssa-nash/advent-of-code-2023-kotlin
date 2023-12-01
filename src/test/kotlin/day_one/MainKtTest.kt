package day_one

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class MainKtTest {
    private val testSequence = sequenceOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet")
    private val testSequence2 = sequenceOf(
        "two1nine",
        "eightwothree",
        "abcone2threexyz",
        "xtwone3four",
        "4nineeightseven2",
        "zoneight234",
        "7pqrstsixteen"
    )

    @Test
    fun testGetCalibrationFromInputPart1() {
        val expected = 142
        val actual = getCalibrationFromInputPart1(testSequence)
        assertEquals(expected, actual)
    }

    @Test
    fun testGetCalibrationFromInputPart2() {
        val expected = 281
        val actual = getCalibrationFromInputPart2(testSequence2)
        assertEquals(expected, actual)
    }
}