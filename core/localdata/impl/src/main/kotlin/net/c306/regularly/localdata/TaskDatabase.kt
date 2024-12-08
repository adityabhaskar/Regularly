package net.c306.regularly.localdata

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
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
@TypeConverters(Converters::class)
internal abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}