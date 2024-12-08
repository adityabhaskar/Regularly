package net.c306.conventions.shared

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinTopLevelExtension

/**
 * The Version Catalog.
 */
internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * Sets up core config for all Android modules - application and library.
 */
internal inline fun <reified T : KotlinTopLevelExtension> Project.configureAndroidModule(
    extension: CommonExtension<*, *, *, *, *, *>,
) {
    extension.apply {
        defaultConfig {
            minSdk = libs.findVersion("minSdk").get().toString().toInt()
            compileSdk = libs.findVersion("compileSdk").get().toString().toInt()
        }

        addBaseDependencies<T>()

        dependencies {
//            add("lintChecks", project(path = ":checks"))
//            add("implementation", libs.findLibrary("jakewharton-timber").get())
        }
    }
}

/**
 * Adds the base dependencies that every module needs.
 */
internal inline fun <reified T : KotlinTopLevelExtension> Project.addBaseDependencies() {
    dependencies {
        add("implementation", libs.findLibrary("kotlin.stdlib").get())
        add("implementation", libs.findLibrary("javax.inject").get())
        add("testImplementation", libs.findLibrary("junit").get())
        add("testImplementation", libs.findLibrary("testParameterInjector").get())
        add("implementation", libs.findLibrary("kotlinx.collections.immutable").get())
    }
    setupKotlinCompilerOptions<T>()
}

private inline fun <reified T : KotlinTopLevelExtension> Project.setupKotlinCompilerOptions() = configure<T> {
    when (this) {
        is KotlinAndroidProjectExtension -> compilerOptions
        is KotlinJvmProjectExtension -> compilerOptions
        else -> TODO("Unsupported project extension $this ${T::class}")
    }.apply {
        jvmToolchain(libs.findVersion("java").get().toString().toInt())

        val warningsAsErrors: String? by project
        allWarningsAsErrors = warningsAsErrors.toBoolean()

        freeCompilerArgs.addAll(
            // https://kotlinlang.org/docs/java-to-kotlin-interop.html#default-methods-in-interfaces
            "-Xjvm-default=all",
            "-opt-in=kotlin.RequiresOptIn",
            // Enable experimental coroutines APIs, including Flow
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        )
    }
}

/**
 * Checks if any TestFixtures files exist.
 */
internal fun Project.hasTestFixtures() = projectDir.resolve("src/testFixtures").exists() &&
    projectDir.resolve("src/testFixtures")
        .walkBottomUp()
        .firstOrNull { it.isFile } != null