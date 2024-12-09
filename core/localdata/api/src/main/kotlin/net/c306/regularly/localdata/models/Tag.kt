package net.c306.regularly.localdata.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * Storage data model for a tag including its tasks.
 *
 * @property tag Data for the tag.
 * @property tasks List of tasks that have this tag.
 */
data class Tag(
    @Embedded val tag: TagEntity,
    @Relation(
        parentColumn = "_id",
        entityColumn = "_id",
        associateBy = Junction(
            value = TaskAndTagEntity::class,
            parentColumn = "tagid",
            entityColumn = "taskid",
        ),
    )
    val tasks: List<TaskEntity>,
)