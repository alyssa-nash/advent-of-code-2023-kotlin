package day_one

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class MainKtTest {
    private val testSequence = sequenceOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet")

    @Test
    fun testGetCalibrationFromInput() {
        val expected = 142
        val actual = getCalibrationFromInput(testSequence)
        assertEquals(expected, actual)
    }
}