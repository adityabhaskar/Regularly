package net.c306.regularly.localdata.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data storage model for a task.
 *
 * The column names in the table are kept to stay compatible with the old Regularly app.
 */
@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") val id: Long,
    @ColumnInfo(name = "tagname") val name: String,
    @ColumnInfo(name = "position") val order: Int,
)