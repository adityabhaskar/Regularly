package net.c306.regularly.core.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/** Creates two previews on a Pixel 7 device for light and dark mode. */
@Preview(
    name = "Light",
    device = Devices.PIXEL_7,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
@Preview(
    name = "Dark",
    device = Devices.PIXEL_7,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF000000,
    showBackground = true,
)
@Preview(
    name = "Narrow device",
    device = "spec:width=945px,height=2400px",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
@Preview(
    name = "Scaled font",
    device = Devices.PIXEL_7,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    fontScale = 1.5f,
)
annotation class PreviewPhoneBothMode

/** Creates two previews on a Pixel tablet device for light and dark mode. */
@Preview(
    name = "Light",
    device = "spec:parent=pixel_tablet,orientation=portrait",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
@Preview(
    name = "Dark",
    device = "spec:parent=pixel_tablet,orientation=portrait",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF000000,
    showBackground = true,
)
@Preview(
    name = "Scaled font",
    device = "spec:parent=pixel_tablet,orientation=portrait",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    fontScale = 1.5f,
)
annotation class PreviewTabletBothMode