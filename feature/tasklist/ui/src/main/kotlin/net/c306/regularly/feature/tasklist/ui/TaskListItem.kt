package net.c306.regularly.feature.tasklist.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.datetime.*
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import net.c306.regularly.core.theme.AppTheme
import net.c306.regularly.core.theme.LocalIsDarkMode
import net.c306.regularly.core.utils.PreviewPhoneBothMode

/**
 * UI model for a task list item.
 *
 * @property id Unique id for the task
 * @property name Task name
 * @property dueDate Due date of the task. Provide `null` if no due date set.
 */
data class TaskListItem(
    val id: Long,
    val name: String,
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
        Row(
            Modifier
                // TODO: 10/12/2024 To support compact mode, override the composition local to make
                //  this 36dp
                .minimumInteractiveComponentSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.weight(1f))
            // TODO: 10/12/2024 Replace this with coloured dots maybe
            Text(
                text = item.dueDate?.toRelativeString(today).orEmpty(),
                style = MaterialTheme.typography.bodySmall.merge(
                    item.dueDate?.toRelativeDateTextStyle(today) ?: TextStyle(),
                ),
            )
        }
    }
}

@Composable
private fun LocalDate.toRelativeString(todayOverride: LocalDate? = null): String {
    val today = todayOverride ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    return when {
        today == this -> {
            stringResource(R.string.date_relative_today)
        }

        today.daysUntil(this) == 1 -> {
            stringResource(R.string.date_relative_tomorrow)
        }

        today.daysUntil(this) == -1 -> {
            stringResource(R.string.date_relative_yesterday)
        }

        today.monthsUntil(this) == 0 && today.daysUntil(this) < 0 -> {
            stringResource(R.string.date_relative_days_in_past, this.daysUntil(today))
        }

        today.yearsUntil(this) == 0 && today.monthsUntil(this) < 0 -> {
            stringResource(R.string.date_relative_months_in_past, this.monthsUntil(today))
        }

        today.monthsUntil(this) == 0 && today.daysUntil(this) > 0 -> {
            stringResource(R.string.date_relative_days_in_future, today.daysUntil(this))
        }

        today.yearsUntil(this) == 0 && today.monthsUntil(this) > 0 -> {
            stringResource(R.string.date_relative_months_in_future, today.monthsUntil(this))
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

private data class DueDateColours(
    val longOverDue: TextStyle,
    val recentlyOverDue: TextStyle,
    val comingSoon: TextStyle,
    val farInFuture: TextStyle,
) {
    companion object {
        val light = DueDateColours(
            longOverDue = TextStyle(
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFB71C1C),
            ),
            recentlyOverDue = TextStyle(color = Color(0xFFBF360C)),
            comingSoon = TextStyle(color = Color(0xFF33691E)),
            farInFuture = TextStyle(color = Color(0xFF1B5E20)),
        )

        val dark = DueDateColours(
            longOverDue = TextStyle(
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFE57373),
            ),
            recentlyOverDue = TextStyle(color = Color(0xFFFF8A65)),
            comingSoon = TextStyle(color = Color(0xFFAED581)),
            farInFuture = TextStyle(color = Color(0xFF81C784)),
        )
    }
}

@Composable
private fun LocalDate.toRelativeDateTextStyle(todayOverride: LocalDate? = null): TextStyle {
    val today = todayOverride ?: Clock.System.todayIn(TimeZone.currentSystemDefault())

    val daysUntil = today.daysUntil(this)
    val dueDatePalette = if (LocalIsDarkMode.current) DueDateColours.dark else DueDateColours.light

    return when {
        daysUntil < -5 -> dueDatePalette.longOverDue
        daysUntil in -5..-2 -> dueDatePalette.recentlyOverDue
        daysUntil in -1..1 -> TextStyle(color = MaterialTheme.colorScheme.onSurface)
        daysUntil in 2..5 -> dueDatePalette.comingSoon
        else -> dueDatePalette.farInFuture
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
                    dueDate = LocalDate(year = 2024, monthNumber = 8, dayOfMonth = 8),
                ),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                today = LocalDate(year = 2024, monthNumber = 8, dayOfMonth = 8),
            )
            TaskListItem(
                item = TaskListItem(
                    id = 1,
                    name = generateLoremIpsum(words = 4),
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
                    dueDate = LocalDate(year = 2024, monthNumber = 8, dayOfMonth = 8),
                ),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                today = LocalDate(year = 2025, monthNumber = 9, dayOfMonth = 12),
            )
        }
    }
}