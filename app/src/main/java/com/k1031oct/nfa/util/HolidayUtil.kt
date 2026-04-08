package com.k1031oct.nfa.util

import java.time.LocalDate

object HolidayUtil {
    fun getHoliday(date: LocalDate, country: String): String? {
        return when (country) {
            "JP" -> getJPHoliday(date)
            "US" -> getUSHoliday(date)
            else -> null
        }
    }

    private fun getJPHoliday(date: LocalDate): String? {
        val month = date.monthValue
        val day = date.dayOfMonth
        
        return when (month) {
            1 -> if (day == 1) "元日" else if (day == 13) "成人の日" else null
            2 -> if (day == 11) "建国記念の日" else if (day == 23) "天皇誕生日" else null
            3 -> if (day == 20) "春分の日" else null
            4 -> if (day == 29) "昭和の日" else null
            5 -> when (day) {
                3 -> "憲法記念日"
                4 -> "みどりの日"
                5 -> "こどもの日"
                else -> null
            }
            7 -> if (day == 21) "海の日" else null
            8 -> if (day == 11) "山の日" else null
            9 -> if (day == 15) "敬老の日" else if (day == 23) "秋分の日" else null
            10 -> if (day == 13) "スポーツの日" else null
            11 -> if (day == 3) "文化の日" else if (day == 23) "勤労感謝の日" else null
            else -> null
        }
    }

    private fun getUSHoliday(date: LocalDate): String? {
        val month = date.monthValue
        val day = date.dayOfMonth
        return when (month) {
            1 -> if (day == 1) "New Year's Day" else null
            7 -> if (day == 4) "Independence Day" else null
            12 -> if (day == 25) "Christmas Day" else null
            else -> null
        }
    }
}
