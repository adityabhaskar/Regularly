package net.c306.conventions.plugins

import com.android.build.gradle.LibraryExtension
import net.c306.conventions.shared.configureAndroidCompose
import net.c306.conventions.shared.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.attributes.java.TargetJvmEnvironment
import org.gradle.kotlin.dsl.getByType

/**
 * The Compose convention plugin is designed for use in `android-library` modules. It applies
 * relevant plugins, build feature flags, compiler options and core dependencies.
 */
class ComposeLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply {
                val id = libs.findPlugin("paparazzi").get().get().pluginId
                plugin(id)

                plugins.withId(id) {
                    // Defer until afterEvaluate so that testImplementation is created by Android plugin.
                    afterEvaluate {
                        dependencies.constraints {
                            add("testImplementation", "com.google.guava:guava") {
                                attributes {
                                    attribute(
                                        TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
                                        objects.named(
                                            TargetJvmEnvironment::class.java,
                                            TargetJvmEnvironment.STANDARD_JVM,
                                        ),
                                    )
                                }
                                because(
                                    """
                                        LayoutLib and sdk-common depend on Guava's -jre published 
                                        variant. See 
                                        https://github.com/cashapp/paparazzi/issues/906.
                                    """.trimIndent(),
                                )
                            }
                        }
                    }
                }
            }

            configureAndroidCompose(extensions.getByType<LibraryExtension>())
        }
    }
}