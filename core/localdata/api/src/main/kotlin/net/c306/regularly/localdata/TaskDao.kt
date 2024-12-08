package net.c306.regularly.localdata

import androidx.room.*
import net.c306.regularly.localdata.models.*

/**
 * Data access object for tasks.
 */
@Dao
interface TaskDao {
    /**
     * Returns all tasks with their associated tags.
     */
    @Transaction
    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>

    /**
     * Returns all tags with their associated tasks.
     */
    @Transaction
    @Query("SELECT * FROM tags")
    suspend fun getAllTags(): List<TagWithTasks>

    /**
     * Inserts a task into the database. Returns the [TaskEntity.id] for the inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    /**
     * Inserts a tag into the database. Returns the [TagEntity.id] for the inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(task: TagEntity): Long

    /**
     * Inserts a task and tag association into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskAndTag(task: TaskAndTagEntity)
}