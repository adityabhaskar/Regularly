package net.c306.conventions.shared

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

/**
 * This extension function applies the correct flags and dependencies to the module represented by
 * the [Project].
 */
internal fun Project.configureAndroidCompose(extension: CommonExtension<*, *, *, *, *, *>) {
    CreateSnapshotsDirTask.register(this)

    extension.apply {
        with(pluginManager) {
            apply(libs.findPlugin("jetbrains.compose").get().get().pluginId)
        }

        extensions.configure(ComposeCompilerGradlePluginExtension::class.java) {
            reportsDestination.set(layout.buildDirectory.dir("compose_compiler"))
        }

        dependencies {
            val bom = libs.findLibrary("androidx.compose.bom").get()
            add("implementation", platform(bom))
            add(
                configurationName = "implementation",
                dependencyNotation = libs.findLibrary("androidx.compose.ui").get(),
            )
            add(
                configurationName = "implementation",
                dependencyNotation = libs.findLibrary("androidx.compose.ui.graphics").get(),
            )
            // Animations
            add(
                configurationName = "implementation",
                dependencyNotation = libs.findLibrary("androidx.compose.animation").get(),
            )

            // Tooling support (Previews, etc.)
            add(
                configurationName = "implementation",
                dependencyNotation = libs.findLibrary("androidx.compose.ui.tooling").get(),
            )
            add(
                configurationName = "implementation",
                dependencyNotation = libs.findLibrary("androidx.compose.ui.tooling.preview").get(),
            )

            // Material 3
            add(
                configurationName = "implementation",
                dependencyNotation = libs.findLibrary("androidx.compose.material3").get(),
            )
            add(
                configurationName = "implementation",
                dependencyNotation = libs.findLibrary("androidx.compose.material3.windowSize")
                    .get(),
            )
            add(
                configurationName = "debugImplementation",
                dependencyNotation = libs.findLibrary("androidx.compose.ui.test.manifest").get(),
            )

            // Android test
            add("androidTestImplementation", platform(bom))
            add(
                configurationName = "androidTestImplementation",
                dependencyNotation = libs.findLibrary("androidx.compose.ui.test.manifest").get(),
            )
            add(
                configurationName = "androidTestImplementation",
                dependencyNotation = libs.findLibrary("androidx.compose.ui.test.junit4").get(),
            )
        }
    }
}