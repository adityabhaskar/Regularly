package net.c306.conventions.plugins

import com.android.build.api.dsl.ApplicationExtension
import net.c306.conventions.shared.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * The Compose convention plugin is designed for use in `android-application` modules. It applies
 * relevant plugins, build feature flags, compiler options and core dependencies.
 */
class ComposeApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureAndroidCompose(extensions.getByType<ApplicationExtension>())
        }
    }
}