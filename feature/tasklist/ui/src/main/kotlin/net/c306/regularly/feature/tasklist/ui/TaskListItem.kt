package net.c306.regularly.feature.tasklist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import net.c306.regularly.core.theme.AppTheme
import net.c306.regularly.core.utils.PreviewPhoneBothMode

/**
 * UI model for a task list item.
 *
 * @property id Unique id for the task
 * @property name Task name
 * @property description Task description
 */
data class TaskListItem(
    val id: Long,
    val name: String,
    val description: String,
)

@Composable
internal fun TaskListItem(
    item: TaskListItem,
    onClick: (id: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = { onClick(item.id) },
        modifier = modifier,
        shape = RectangleShape,
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@PreviewPhoneBothMode
@Composable
private fun Preview() {
    AppTheme {
        TaskListItem(
            item = TaskListItem(
                id = 1,
                name = "Title",
                description = "Description",
            ),
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}