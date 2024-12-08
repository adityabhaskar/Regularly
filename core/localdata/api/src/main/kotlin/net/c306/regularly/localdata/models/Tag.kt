package net.c306.regularly.localdata.models

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Storage data model for a tag including its tasks.
 */
data class Tag(
    @Embedded val tag: TagEntity,
    @Relation(
        parentColumn = "_id",
        entityColumn = "tagid",
    )
    val tasks: List<TaskEntity>,
)