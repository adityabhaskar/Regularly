plugins {
    alias(libs.plugins.library.android)
    alias(libs.plugins.compose.library)
}

android {
    namespace = "net.c306.regularly.feature.tasklist.ui"
}

detekt {
    baseline = file("detekt-baseline.xml")
}

dependencies {
    implementation(projects.core.theme)
}