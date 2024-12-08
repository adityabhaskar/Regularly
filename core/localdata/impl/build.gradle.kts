plugins {
    alias(libs.plugins.library.android)

    // Optional: parcelize
    // id("kotlin-parcelize")
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
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}