plugins {
    alias(libs.plugins.library.android)
}

android {
    namespace = "net.c306.regularly.localdata.api"
}

detekt {
    baseline = file("$projectDir/detekt-baseline.xml")
}

dependencies {
    implementation(libs.androidx.room)
}