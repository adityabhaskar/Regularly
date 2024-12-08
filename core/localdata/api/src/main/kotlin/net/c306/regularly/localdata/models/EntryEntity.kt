package net.c306.regularly.localdata.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

/**
 * Data storage model for a task's entries.
 *
 * The column names in the table are kept to stay compatible with the old Regularly app.
 */
@Entity(
    tableName = "log",
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["_id"],
            childColumns = ["taskid"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class EntryEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") val id: Long = 0,
    @ColumnInfo(name = "taskid") val taskId: Int,
    @ColumnInfo(name = "entrydate") val completedDate: LocalDate,
    @ColumnInfo(name = "note") val notes: String,
)