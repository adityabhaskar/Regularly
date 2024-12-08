plugins {
    alias(libs.plugins.library.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "net.c306.regularly.localdata.api"
}

detekt {
    baseline = file("$projectDir/detekt-baseline.xml")
}

dependencies {
    implementation(libs.androidx.room)
    ksp(libs.androidx.room.compiler)
}