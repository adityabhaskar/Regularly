plugins {
    alias(libs.plugins.agp.application) apply false
    alias(libs.plugins.agp.library) apply false
    alias(libs.plugins.kgp.android) apply false
    alias(libs.plugins.kgp.jvm) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.google.hilt) apply false
    alias(libs.plugins.ksp) apply false

    alias(libs.plugins.application) apply false
    alias(libs.plugins.library.android) apply false
    alias(libs.plugins.library.kotlin) apply false
    alias(libs.plugins.compose.application) apply false
    alias(libs.plugins.compose.library) apply false
    alias(libs.plugins.hilt) apply false
}