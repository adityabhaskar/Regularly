plugins {
    `kotlin-dsl`
    alias(libs.plugins.android.lint)
}

group = "net.c306.regularly"
version = "1.0"

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.composeCompiler.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)

    lintChecks(libs.androidx.lint.gradle)
}

gradlePlugin {
    plugins {
        register("androidApplicationPlugin") {
            id = "net.c306.conventions.application"
            implementationClass = "net.c306.conventions.plugins.AndroidApplicationConventionPlugin"
            version = "1.0"
        }
        register("androidLibraryPlugin") {
            id = "net.c306.conventions.library.android"
            implementationClass = "net.c306.conventions.plugins.AndroidLibraryConventionPlugin"
            version = "1.0"
        }
        register("kotlinLibraryPlugin") {
            id = "net.c306.conventions.library.kotlin"
            implementationClass = "net.c306.conventions.plugins.KotlinLibraryConventionPlugin"
            version = "1.0"
        }
        register("composeLibrary") {
            id = "net.c306.conventions.compose.library"
            implementationClass = "net.c306.conventions.plugins.ComposeLibraryConventionPlugin"
            version = "1.0"
        }
        register("composeApplication") {
            id = "net.c306.conventions.compose.application"
            implementationClass = "net.c306.conventions.plugins.ComposeApplicationConventionPlugin"
            version = "1.0"
        }
        register("hilt") {
            id = "net.c306.conventions.hilt"
            implementationClass = "net.c306.conventions.plugins.HiltConventionPlugin"
            version = "1.0"
        }
    }
}