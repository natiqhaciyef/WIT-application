package com.natiqhaciyef.witapplication.util_module_test.helpers

import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.util.common.util.helpers.examTimer
import com.natiqhaciyef.util.common.util.helpers.fromDateToDay
import com.natiqhaciyef.util.common.util.helpers.fromDoubleToTimeLine
import com.natiqhaciyef.util.common.util.helpers.fromStringToMappedDay
import com.natiqhaciyef.util.common.util.helpers.fromStringToMappedTime
import com.natiqhaciyef.util.common.util.helpers.getNow
import com.natiqhaciyef.util.common.util.helpers.majorStringToDateChanger
import com.natiqhaciyef.util.common.util.helpers.monthToString
import com.natiqhaciyef.util.common.util.helpers.ticketTimeCalculator
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month

class DateToLocalTimeChangerTest {

    @Test
    fun `ticket time calculator returns success`(){
        val result = ticketTimeCalculator("12:45", "21:15")
        val template = fromDoubleToTimeLine(8.5)

        assertThat(result).isEqualTo(template)
    }

    @Test
    fun `from date to day returns success`(){
        val result1 = fromDateToDay("01")
        val result2 = fromDateToDay("11")
        val template1 = 1
        val template2 = 0

        assertThat(result1).isEqualTo(template1)
        assertThat(result2).isEqualTo(template2)
    }

    @Test
    fun `from double to time returns success`(){
        val result = fromDoubleToTimeLine(12.3)
        val template = "12 hours\n18 minutes"

        assertThat(result).isEqualTo(template)
    }

    @Test
    fun `month to string returns success`(){
        val result = monthToString("06")

        assertThat(result).isEqualTo("June")
    }

    @Test
    fun `exam timer returns success`(){
        val result = examTimer(1200)
        val template = "20 : 00"

        assertThat(result).isEqualTo(template)
    }

    @Test
    fun `major string to date changer returns success`(){
        val result = majorStringToDateChanger("21.12.2023 13:55")
        val template = "1:55 PM (21 December, 2023)"

        assertThat(result).isEqualTo(template)
    }

    @Test
    fun `from string to mapped time returns success`(){
        val result = fromStringToMappedTime("14:14")

        assertThat(result).isEqualTo("2:14 PM")
    }

    @Test
    fun `from string to mapped day returns success`(){
        val result = fromStringToMappedDay("01.06")
        val template = "01 June"

        assertThat(result).isEqualTo(template)
    }

    @Test
    fun `get now returns success`(){
        val result = getNow(LocalDateTime.of(2023, Month.JUNE, 8, 8,12))
        val template = "08.06.2023 08:12"

        assertThat(result).isEqualTo(template)
    }

    @Test
    fun `get now with input is not contains hours and minutes returns error`(){
        val result = getNow(LocalDateTime.of(2023, Month.JUNE, 8, 0,0))
        val template = "08.06.2023 00:00"

        assertThat(result).isNotEqualTo(template)
        assertThat(result).isNull()
    }

    @Test
    fun `from string to mapped day with non-correct input returns error`(){
        val result1 = fromStringToMappedDay("01.3")
        val result2 = fromStringToMappedDay("01.19")

        assertThat(result1).isNull()
        assertThat(result2).isNull()
    }

    @Test
    fun `from string to mapped time with non-correct input returns error`(){
        val result = fromStringToMappedTime("2:14")

        assertThat(result).isNull()
    }

    @Test
    fun `major string to date changer with non-correct input returns error`(){
        val result1 = majorStringToDateChanger("21.12.2023 3:00")
        val result2 = majorStringToDateChanger("21.TB.2023 A3:00")

        assertThat(result1).isNull()
        assertThat(result2).isNull()
    }

    @Test
    fun `major string to date changer with non-correct time expression returns error`(){
        val result1 = majorStringToDateChanger("21.24.2023 13:00")
        val result2 = majorStringToDateChanger("21.12.2023 13:65")
        val result3 = majorStringToDateChanger("21.12.2023 26:00")
        val result4 = majorStringToDateChanger("40.12.2023 13:00")

        assertThat(result1).isNull()
        assertThat(result2).isNull()
        assertThat(result3).isNull()
        assertThat(result4).isNull()
    }

    @Test
    fun `exam timer with minus integer returns error`(){
        val result = examTimer(-1200)

        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `month to string with input length less than 2 returns error`(){
        val result = monthToString("6")

        assertThat(result).isNotEqualTo("June")
    }

    @Test
    fun `month to string with input contains alphabetic symbols returns error`(){
        val result = monthToString("six")

        assertThat(result).isNotEqualTo("June")
    }

    @Test
    fun `from double to time with minus numbers returns error`(){
        val result = fromDoubleToTimeLine(-8.1)

        assertThat(result).isNull()
    }

    @Test
    fun `ticket time calculator with non-correct range of time returns error`(){
        val result = ticketTimeCalculator("12:45", "08:15")
        val template = fromDoubleToTimeLine(4.5)

        assertThat(result).isNotEqualTo(template)
    }

    @Test
    fun `ticket time calculator with input contains non-digit symbols returns error`(){
        val result = ticketTimeCalculator("alman", "ghetto")

        assertThat(result).isNull()
    }


}