package net.c306.regularly.localdata.models

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data model representing a task with its entries.
 */
data class TaskWithEntries(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "_id",
        entityColumn = "taskid",
    )
    val entries: List<TaskEntry>,
)