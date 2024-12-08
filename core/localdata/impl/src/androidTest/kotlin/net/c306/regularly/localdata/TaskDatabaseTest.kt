package net.c306.regularly.localdata

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import kotlinx.coroutines.test.runTest
import net.c306.regularly.localdata.models.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TaskDatabaseTest {
    private lateinit var db: TaskDatabase
    private lateinit var dao: TaskDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = TaskDatabase::class.java,
        ).build()
        dao = db.taskDao()
    }

    @Test
    fun when_initialised_the_task_and_tag_tables_are_empty() = runTest {
        val tasks = dao.getAllTasks()
        val tags = dao.getAllTags()
        assertTrue(tasks.isEmpty())
        assertTrue(tags.isEmpty())
    }

    @Test
    fun when_a_task_is_added_it_is_in_the_task_table() = runTest {
        val task = createTestTaskEntity("Test task")
        dao.insertTask(task)
        val tasks = dao.getAllTasks()

        assertTrue(
            task.isSame(tasks[0].task),
        )
    }

    @Test
    fun when_a_tag_is_added_it_is_in_the_tag_table() = runTest {
        val tag = TagEntity(
            name = "Test Tag",
            order = 0,
        )
        dao.insertTag(tag)
        val tags = dao.getAllTags()

        assertTrue(tag.isSame(tags[0].tag))
    }

    @Test
    fun when_a_tag_is_added_to_a_task_then_it_is_in_the_task_response() = runTest {
        val task = createTestTaskEntity("Test Task")
        val tag = TagEntity(name = "Test Tag", order = 0)
        val taskId = dao.insertTask(task)
        val tagId = dao.insertTag(tag)

        dao.insertTaskAndTag(TaskAndTagEntity(taskId = taskId, tagId = tagId))

        val tasks = dao.getAllTasks()

        val expectedTaskAndTag = Task(
            task = task.copy(id = taskId),
            tags = listOf(tag.copy(id = tagId)),
        )

        assertEquals(expectedTaskAndTag, tasks[0])
    }

    @Test
    fun when_a_tag_is_added_to_a_task_then_it_is_in_tag_response() = runTest {
        val task = createTestTaskEntity("Test Task")
        val tag = TagEntity(name = "Test Tag", order = 0)
        val taskId = dao.insertTask(task)
        val tagId = dao.insertTag(tag)

        dao.insertTaskAndTag(TaskAndTagEntity(taskId = taskId, tagId = tagId))

        val tags = dao.getAllTags()

        val expectedTagWithTasks = TagWithTasks(
            tag = tag.copy(id = tagId),
            tasks = listOf(task.copy(id = taskId)),
        )

        assertEquals(expectedTagWithTasks, tags[0])
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}