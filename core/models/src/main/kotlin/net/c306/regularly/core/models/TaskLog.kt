package net.c306.regularly.core.models

import kotlinx.datetime.LocalDate

internal data class TaskLog(
    val id: Int,
    val taskId: Int,
    val completedDate: LocalDate,
    val notes: String,
)