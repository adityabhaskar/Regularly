plugins {
    alias(libs.plugins.library.android)
    // OR
    // alias(libs.plugins.library.kotlin)

    // Optional: parcelize
    // id("kotlin-parcelize")
    // Optional: use compose in module
    // alias(libs.plugins.compose.library)
    // Optional: Use hilt in module
    // alias(libs.plugins.hilt)
}

android {
    namespace = "NAMESPACE_PLACEHOLDER"
}

dependencies {
}