package net.c306.regularly.localdata

import androidx.room.TypeConverter
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

internal class Converters {
    @TypeConverter
    fun fromDate(date: LocalDate): String = date.toString()

    @TypeConverter
    fun toDate(dateString: String): LocalDate = LocalDate.parse(dateString)

    @TypeConverter
    fun fromDatePeriod(period: DatePeriod): String = period.toString()

    @TypeConverter
    fun toDatePeriod(periodString: String): DatePeriod = DatePeriod.parse(periodString)

    @TypeConverter
    fun fromLocalTime(time: LocalTime): String = time.toString()

    @TypeConverter
    fun toLocalTime(timeString: String): LocalTime = LocalTime.parse(timeString)
}