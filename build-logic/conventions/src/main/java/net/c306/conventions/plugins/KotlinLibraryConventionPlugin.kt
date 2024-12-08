package net.c306.conventions.plugins

import net.c306.conventions.shared.addBaseDependencies
import net.c306.conventions.shared.hasTestFixtures
import net.c306.conventions.shared.libs
import net.c306.conventions.shared.setupDetekt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

/**
 * Configures the Android application module.
 *
 * Core responsibilities:
 * 1. Apply Kotlin and ktlint plugins
 * 2. Set up Java version
 * 3. Add core dependencies
 */
class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("kgp.jvm").get().get().pluginId)
                apply(libs.findPlugin("android.lint").get().get().pluginId)

                setupDetekt()

                if (hasTestFixtures()) {
                    apply("java-test-fixtures")
                }
            }

            addBaseDependencies<KotlinJvmProjectExtension>()
        }
    }
}