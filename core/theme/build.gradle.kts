plugins {
    alias(libs.plugins.library.android)
    alias(libs.plugins.compose.library)
}

android {
    namespace = "net.c306.regularly.core.theme"
}

detekt {
    baseline = file("detekt-baseline-debug.xml")
}

dependencies {
}