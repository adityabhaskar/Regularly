package net.c306.regularly.localdata

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import net.c306.regularly.localdata.models.Task

/**
 * Data access object for tasks.
 */
@Dao
interface TaskDao {
    @Transaction
    @Query("SELECT * FROM tasks")
    fun getAll(): List<Task>
}