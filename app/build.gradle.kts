plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.compose.application)
    alias(libs.plugins.hilt)
}

android {
    namespace = "net.c306.regularly"

    defaultConfig {
        applicationId = "net.c306.regularly"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(projects.core.theme)
    implementation(projects.core.models)
    implementation(projects.core.localdata.api)
    implementation(projects.core.localdata.impl)

    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.activity.compose)
}