package net.c306.regularly.localdata.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * Data storage model for associated tasks and tags.
 *
 * The column names in the table are kept to stay compatible with the old Regularly app.
 */
@Entity(
    tableName = "taskTags",
    primaryKeys = ["taskid", "tagid"],
    // Define taskId and tagId as foreign keys with cascade so rows with them get deleted if the
    // related tag or task is deleted.
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["_id"],
            childColumns = ["taskid"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = TagEntity::class,
            parentColumns = ["_id"],
            childColumns = ["tagid"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class TaskAndTagEntity(
    @ColumnInfo(name = "_id") val id: Long = 0,
    @ColumnInfo("taskid", index = true) val taskId: Long,
    @ColumnInfo("tagid", index = true) val tagId: Long,
)