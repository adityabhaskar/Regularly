package net.c306.regularly.localdata.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * Storage data model for a task including its tags.
 */
data class Task(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "_id",
        entityColumn = "_id",
        associateBy = Junction(TaskAndTagEntity::class)
    )
    val tags: List<TagEntity>,
)