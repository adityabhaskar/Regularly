package net.c306.conventions.plugins

import net.c306.conventions.shared.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * The Hilt convention plugin applies relevant plugins (ksp and hilt) and adds core Hilt
 * dependencies.
 *
 * Included dependencies are:
 * * google-hilt-android (impl)
 * * google-hilt-compiler (ksp)
 * * android-hilt-compiler (ksp)
 *
 * The first two provide core hilt support. The third one allows us to use dependencies that
 * integrate Hilt with other Jetpack libraries, e.g. Room, Compose, Work Manager, and Navigation.
 * Those dependencies are not provided by this plugin, and should be directly applied only to
 * modules where they are needed.
 */
@Suppress("UnstableApiUsage")
class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("google.hilt").get().get().pluginId)
                apply(libs.findPlugin("ksp").get().get().pluginId)
            }

            dependencies {
                "implementation"(libs.findLibrary("google.hilt.android").get())
//                "implementation"(libs.findLibrary("androidx.hilt.work").get())
                "ksp"(libs.findLibrary("google.hilt.compiler").get())
                "ksp"(libs.findLibrary("androidx.hilt.compiler").get())
            }
        }
    }
}