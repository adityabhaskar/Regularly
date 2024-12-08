package net.c306.regularly.core.models

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

internal data class Task(
    val id: Int,
    val name: String,
    val periodDays: Int,
    val firstDue: LocalDate,
    val lastPerformed: LocalDate,
    val lastNotified: LocalDate,
    val created: LocalDate,
    val details: String,
    val notificationsEnabled: Boolean,
    val notificationTimeOfDay: LocalTime,
    val notificationsPeriod: DatePeriod,
)