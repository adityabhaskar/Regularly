package net.c306.regularly.localdata.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data storage model for associated tasks and tags.
 *
 * The column names in the table are kept to stay compatible with the old Regularly app.
 */
@Entity(tableName = "taskTags")
data class TaskAndTagEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") val id: Long,
    @ColumnInfo("taskid") val taskId: Int,
    @ColumnInfo("tagid") val tagId: Int,
)