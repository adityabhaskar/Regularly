package net.c306.regularly.feature.tasklist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.datetime.LocalDate
import net.c306.regularly.core.theme.AppTheme
import net.c306.regularly.core.utils.PreviewPhoneBothMode
import net.c306.regularly.core.utils.PreviewTabletBothMode

@Composable
internal fun TaskList(
    items: ImmutableList<TaskListItem>,
    onItemClick: (id: Long) -> Unit,
    modifier: Modifier = Modifier,
    contentHorizontalPadding: Dp = Dp.Unspecified,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(
            horizontal = if (contentHorizontalPadding.isSpecified) {
                contentHorizontalPadding
            } else {
                0.dp
            },
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            count = items.size,
            key = { index -> items[index].id },
        ) { index ->
            TaskListItem(
                item = items[index],
                onClick = onItemClick,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@PreviewPhoneBothMode
@PreviewTabletBothMode
@Composable
private fun TaskListPreview() {
    AppTheme {
        val horizontalPadding = (LocalConfiguration.current.screenWidthDp.dp - 400.dp)
            .coerceAtLeast(16.dp) / 2
        TaskList(
            items = List(size = 100) {
                TaskListItem(
                    id = it.toLong(),
                    name = generateLoremIpsum(words = 4),
                    description = generateLoremIpsum(words = 20),
                    dueDate = LocalDate(year = 2024, monthNumber = 8, dayOfMonth = 8),
                )
            }.toPersistentList(),
            onItemClick = {},
            contentHorizontalPadding = horizontalPadding,
        )
    }
}

internal fun generateLoremIpsum(words: Int) = LoremIpsum(words).values
    .joinToString()
    .replace("\n", "")