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

}

gradlePlugin {
    plugins {
        // TODO: 07/12/2024 Create plugins here
    }
}