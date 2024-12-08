package net.c306.regularly.localdata.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

/**
 * Data storage model for a task.
 *
 * The column names in the table are kept to stay compatible with the old Regularly app.
 */
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") val id: Long = 0,
    val name: String,
    @ColumnInfo("period") val periodDays: Int,
    @ColumnInfo("firstdue") val firstDue: LocalDate,
    @ColumnInfo("lastperformed") val lastPerformed: LocalDate?,
    @ColumnInfo("lastnotified") val lastNotified: LocalDate?,
    val created: LocalDate,
    val details: String,
    @ColumnInfo("notifications_enabled") val areNotificationsEnabled: Boolean,
    @ColumnInfo("notifications_time") val notificationTimeOfDay: LocalTime?,
    @ColumnInfo("notifications_period") val notificationsPeriod: DatePeriod?,
) {
    /** Checks whether non `id` properties are equal. */
    fun isSame(other: TaskEntity) = name == other.name &&
        periodDays == other.periodDays &&
        firstDue == other.firstDue &&
        lastPerformed == other.lastPerformed &&
        lastNotified == other.lastNotified &&
        created == other.created &&
        details == other.details &&
        areNotificationsEnabled == other.areNotificationsEnabled &&
        notificationTimeOfDay == other.notificationTimeOfDay &&
        notificationsPeriod == other.notificationsPeriod
}

/**
 * Helper function to create a [TaskEntity] instance with a given [name].
 */
fun createTestTaskEntity(name: String) = TaskEntity(
    name = name,
    periodDays = 7,
    firstDue = LocalDate(2024, 12, 1),
    lastPerformed = null,
    lastNotified = null,
    created = LocalDate(2023, 12, 1),
    details = "Test details",
    areNotificationsEnabled = true,
    notificationTimeOfDay = null,
    notificationsPeriod = null,
)