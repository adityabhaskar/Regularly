package net.c306.regularly.feature.tasklist.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.datetime.*
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import net.c306.regularly.core.theme.AppTheme
import net.c306.regularly.core.utils.PreviewPhoneBothMode

/**
 * UI model for a task list item.
 *
 * @property id Unique id for the task
 * @property name Task name
 * @property description Task description
 * @property dueDate Due date of the task. Provide `null` if no due date set.
 */
data class TaskListItem(
    val id: Long,
    val name: String,
    val description: String,
    val dueDate: LocalDate?,
)

@Composable
internal fun TaskListItem(
    item: TaskListItem,
    onClick: (id: Long) -> Unit,
    modifier: Modifier = Modifier,
    today: LocalDate? = null,
) {
    Card(
        onClick = { onClick(item.id) },
        modifier = modifier,
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Column(
            modifier = Modifier
                .minimumInteractiveComponentSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = item.dueDate?.toRelativeString(today).orEmpty(),
                    style = MaterialTheme.typography.bodySmall,
                    color = item.dueDate?.toRelativeColour(today) ?: Color.Unspecified,
                )
            }
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun LocalDate.toRelativeString(todayOverride: LocalDate? = null): String {
    val today = todayOverride ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    return when {
        today == this -> {
            "Today"
        }

        today.daysUntil(this) == 1 -> {
            "Tomorrow"
        }

        today.daysUntil(this) == -1 -> {
            "Yesterday"
        }

        today.monthsUntil(this) == 0 && today.daysUntil(this) < 0 -> {
            "${this.daysUntil(today)} days ago"
        }

        today.yearsUntil(this) == 0 && today.monthsUntil(this) < 0 -> {
            "${this.monthsUntil(today)} months ago"
        }

        today.monthsUntil(this) == 0 && today.daysUntil(this) > 0 -> {
            "in ${today.daysUntil(this)} days"
        }

        today.yearsUntil(this) == 0 && today.monthsUntil(this) > 0 -> {
            "in ${today.monthsUntil(this)} months"
        }

        else -> {
            this.format(OtherDateFormat)
        }
    }
}

private val OtherDateFormat = LocalDate.Format {
    monthName(MonthNames.ENGLISH_ABBREVIATED)
    char(' ')
    dayOfMonth()
    chars(", ")
    year()
}

@Composable
fun LocalDate.toRelativeColour(todayOverride: LocalDate? = null): Color {
    val today = todayOverride ?: Clock.System.todayIn(TimeZone.currentSystemDefault())

    val daysUntil = today.daysUntil(this)

    if (daysUntil < 0) {
        return lerp(
            start = Color.Red,
            stop = MaterialTheme.colorScheme.onSurface,
            fraction = (today.daysUntil(this) + 30) / 30f,
        )
    } else {
        return lerp(
            start = MaterialTheme.colorScheme.onSurface,
            stop = Color.Green,
            fraction = today.daysUntil(this) / 30f,
        )
    }
}

@PreviewPhoneBothMode
@Composable
private fun TaskListItemPreview() {
    AppTheme {
        Column {
            TaskListItem(
                item = TaskListItem(
                    id = 1,
                    name = generateLoremIpsum(words = 4),
                    description = generateLoremIpsum(words = 20),
                    dueDate = LocalDate(year = 2024, monthNumber = 8, dayOfMonth = 8),
                ),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                today = LocalDate(year = 2023, monthNumber = 1, dayOfMonth = 2),
            )
            TaskListItem(
                item = TaskListItem(
                    id = 1,
                    name = generateLoremIpsum(words = 4),
                    description = "",
                    dueDate = LocalDate(year = 2024, monthNumber = 8, dayOfMonth = 8),
                ),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                today = LocalDate(year = 2023, monthNumber = 12, dayOfMonth = 12),
            )
            TaskListItem(
                item = TaskListItem(
                    id = 1,
                    name = generateLoremIpsum(words = 4),
                    description = "",
                    dueDate = LocalDate(year = 2024, monthNumber = 8, dayOfMonth = 8),
                ),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                today = LocalDate(year = 2024, monthNumber = 8, dayOfMonth = 4),
            )
            TaskListItem(
                item = TaskListItem(
                    id = 1,
                    name = generateLoremIpsum(words = 4),
                    description = "",
                    dueDate = LocalDate(year = 2024, monthNumber = 8, dayOfMonth = 8),
                ),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                today = LocalDate(year = 2024, monthNumber = 8, dayOfMonth = 12),
            )
            TaskListItem(
                item = TaskListItem(
                    id = 1,
                    name = generateLoremIpsum(words = 4),
                    description = "",
                    dueDate = LocalDate(year = 2024, monthNumber = 8, dayOfMonth = 8),
                ),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                today = LocalDate(year = 2024, monthNumber = 12, dayOfMonth = 12),
            )
            TaskListItem(
                item = TaskListItem(
                    id = 1,
                    name = generateLoremIpsum(words = 4),
                    description = "",
                    dueDate = LocalDate(year = 2024, monthNumber = 8, dayOfMonth = 8),
                ),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                today = LocalDate(year = 2025, monthNumber = 9, dayOfMonth = 12),
            )
        }
    }
}