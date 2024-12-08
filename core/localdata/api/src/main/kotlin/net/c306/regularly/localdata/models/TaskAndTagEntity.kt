package net.c306.regularly.localdata.models

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Data storage model for associated tasks and tags.
 *
 * The column names in the table are kept to stay compatible with the old Regularly app.
 */
@Entity(
    tableName = "taskTags",
    primaryKeys = ["taskid", "tagid"],
)
data class TaskAndTagEntity(
    @ColumnInfo(name = "_id") val id: Long = 0,
    @ColumnInfo("taskid", index = true) val taskId: Long,
    @ColumnInfo("tagid", index = true) val tagId: Long,
)