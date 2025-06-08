package net.c306.conventions.shared

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ManagedVirtualDevice
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke

/**
 * Provides AndroidTest dependencies, and sets up Gradle Managed devices to run tests on CI.
 */
internal fun Project.configureAndroidTests(extension: CommonExtension<*, *, *, *, *, *>) {
    with(extension) {
        configureGradleManagedDevices()

        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    dependencies {
        val configuration = "androidTestImplementation"
        add(configuration, libs.findLibrary("junit").get())
        add(configuration, libs.findLibrary("androidx.espresso.core").get())
    }
}

/**
 * This extension function creates a gradle managed device for the receiver project.
 */
private fun CommonExtension<*, *, *, *, *, *>.configureGradleManagedDevices() {
    testOptions {
        managedDevices {
            allDevices {
                maybeCreate("ciDevice", ManagedVirtualDevice::class.java).apply {
                    // Use device profiles you typically see in Android Studio.
                    device = "Pixel 5"
                    // Use only API levels 27 and higher.
                    apiLevel = 33
                    // To include Google services, use the "google"/"google-atd" variants
                    // "aosp-atd"/"google-atd"/"aosp"/"google"
                    systemImageSource = "aosp-atd"
                }
            }
        }
    }
}