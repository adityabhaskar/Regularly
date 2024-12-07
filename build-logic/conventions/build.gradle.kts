plugins {
    `kotlin-dsl`
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
    compileOnly(libs.kotlin.gradlePlugin)

}

gradlePlugin {
    plugins {
        register("androidApplicationPlugin") {
            id = "net.c306.application"
            implementationClass = "net.c306.conventions.AndroidApplicationConventionPlugin"
            version = "1.0"
        }
        register("androidLibraryPlugin") {
            id = "net.c306.library.android"
            implementationClass = "net.c306.conventions.AndroidLibraryConventionPlugin"
            version = "1.0"
        }
        register("kotlinLibraryPlugin") {
            id = "net.c306.library.kotlin"
            implementationClass = "net.c306.conventions.KotlinLibraryConventionPlugin"
            version = "1.0"
        }
    }
}