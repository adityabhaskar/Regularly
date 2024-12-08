package net.c306.regularly.localdata

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import kotlinx.coroutines.test.runTest
import org.junit.After
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
            klass = TaskDatabase::class.java
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

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}