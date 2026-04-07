package com.aliayali.personalfinanceapp.core.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId

class PersianDate {

    fun gregorianToJalali(gy: Int, gm: Int, gd: Int): Triple<Int, Int, Int> {
        fun isGregorianLeap(year: Int): Boolean {
            return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
        }

        val g_days_in_month = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

        var days = 0
        for (y in 1 until gy) {
            days += if (isGregorianLeap(y)) 366 else 365
        }
        for (m in 1 until gm) {
            days += g_days_in_month[m]
            if (m == 2 && isGregorianLeap(gy)) {
                days += 1
            }
        }
        days += gd

        val persianEpochDay = 226895

        var jalaliDays = days - persianEpochDay

        var year = 1
        var leapDayAdjustment = 0

        while (true) {
            val isCurrentLeap =
                (year % 33 == 1 || year % 33 == 5 || year % 33 == 9 || year % 33 == 13 || year % 33 == 17 || year % 33 == 22 || year % 33 == 26 || year % 33 == 30)
            leapDayAdjustment = if (isCurrentLeap) 366 else 365

            if (jalaliDays < leapDayAdjustment) {
                break
            }
            jalaliDays -= leapDayAdjustment
            year++
        }

        val j_days_in_month = intArrayOf(
            0,
            31,
            31,
            31,
            31,
            31,
            31,
            30,
            30,
            30,
            30,
            30,
            29
        )

        var month = 1
        while (true) {
            var daysInMonth = j_days_in_month[month]
            if (month == 12 && (year % 33 == 1 || year % 33 == 5 || year % 33 == 9 || year % 33 == 13 || year % 33 == 17 || year % 33 == 22 || year % 33 == 26 || year % 33 == 30)) {
                daysInMonth = 30
            }

            if (jalaliDays < daysInMonth) {
                break
            }
            jalaliDays -= daysInMonth
            month++
        }
        val day = jalaliDays + 1

        return Triple(year, month, day)
    }

    fun getTodayPersianDate(): String {
        val date = LocalDate.now(ZoneId.of("Asia/Tehran"))
        val (jy, jm, jd) = gregorianToJalali(date.year, date.monthValue, date.dayOfMonth)
        return "$jy/$jm/$jd"
    }
}