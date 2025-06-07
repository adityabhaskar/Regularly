package net.c306.conventions.plugins

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import net.c306.conventions.shared.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

/**
 * Configures the Android library modules.
 *
 * Core responsibilities:
 * 1. Apply Android, Kotlin and Ktlint plugins
 * 2. Set up SDK & Java versions
 * 3. Add core dependencies
 * 4. Add lint checks
 * 5. Set up Android tests & Gradle managed devices if the module has test sources
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val shouldSetupAndroidTests = hasAndroidTests()

            with(pluginManager) {
                apply(libs.findPlugin("agp.library").get().get().pluginId)
                apply(libs.findPlugin("kgp.android").get().get().pluginId)
            }

            setupDetekt()

            extensions.configure<LibraryExtension> {
                configureAndroidModule<KotlinAndroidProjectExtension>(this)

                if (shouldSetupAndroidTests) {
                    configureAndroidTests(this)
                }
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                beforeVariants { variantBuilder ->
                    variantBuilder.enableTestFixtures = hasTestFixtures()
                    // Disable unnecessary Android instrumented tests for the project if there are no
                    // tests in the `androidTest` folder. Otherwise, these projects would be compiled,
                    // packaged, installed and run only to end-up with the following message:
                    //
                    // > Starting 0 tests on AVD
                    variantBuilder.enableAndroidTest = shouldSetupAndroidTests

                    if (variantBuilder.buildType != "debug") {
                        // Disable non-debug variants in libraries
                        variantBuilder.enable = false
                    }
                }
            }
        }
    }

    /**
     * Checks if any AndroidTest files exist.
     */
    private fun Project.hasAndroidTests() = projectDir.resolve("src/androidTest").exists() &&
        projectDir.resolve("src/androidTest")
            .walkBottomUp()
            .firstOrNull { it.isFile } != null
}