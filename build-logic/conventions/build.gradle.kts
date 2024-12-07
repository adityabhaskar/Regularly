plugins {
    `kotlin-dsl`
}

group = "net.c306.regularly"

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
    compileOnly(libs.kotlin.gradlePlugin)

}

gradlePlugin {
    plugins {
        // TODO: 07/12/2024 Create plugins here
        register("androidApplicationPlugin") {
            id = "net.c306.application"
            implementationClass = "net.c306.conventions.AndroidApplicationConventionPlugin"
            version = "1"
        }
        register("androidLibraryPlugin") {
            id = "net.c306.library.android"
            implementationClass = "net.c306.conventions.AndroidLibraryConventionPlugin"
            version = "1"
        }
        register("kotlinLibraryPlugin") {
            id = "net.c306.library.kotlin"
            implementationClass = "net.c306.conventions.KotlinLibraryConventionPlugin"
            version = "1"
        }
    }
}