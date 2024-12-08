plugins {
    alias(libs.plugins.library.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.room)
}

android {
    namespace = "net.c306.regularly.localdata.impl"
}

room {
    schemaDirectory("$projectDir/schemas")
}

ksp {
    arg("room.generateKotlin", "true")
}

dependencies {
    implementation(projects.core.localdata.api)

    implementation(libs.androidx.room)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}