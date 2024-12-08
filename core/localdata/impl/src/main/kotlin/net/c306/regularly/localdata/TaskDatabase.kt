package net.c306.regularly.localdata

import androidx.room.Database
import androidx.room.RoomDatabase
import net.c306.regularly.localdata.models.EntryEntity
import net.c306.regularly.localdata.models.TagEntity
import net.c306.regularly.localdata.models.TaskAndTagEntity
import net.c306.regularly.localdata.models.TaskEntity

@Database(
    entities = [
        TaskEntity::class,
        TagEntity::class,
        TaskAndTagEntity::class,
        EntryEntity::class,
    ],
    version = 1,
)
internal abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}