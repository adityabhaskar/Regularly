package net.c306.conventions.shared

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.gitlab.arturbosch.detekt.report.ReportMergeTask
import org.gradle.api.Project

/**
 * Configures Detekt on the [Project].
 *
 * Enables report generation, report merge, and sets up the configuration.
 */
internal fun Project.setupDetekt() {
    with(pluginManager) {
        apply(libs.findPlugin("detekt").get().get().pluginId)
    }

    tasks.register("reportMerge", ReportMergeTask::class.java) {
        output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.sarif"))
    }

    val reportMerge = tasks.named("reportMerge", ReportMergeTask::class.java)

    reportMerge.configure {
        input.from(tasks.withType(Detekt::class.java).map { it.sarifReportFile })
    }

    // enable reports
    tasks.withType(Detekt::class.java).configureEach {
        reports {
            xml.required.set(false)
            html.required.set(true)
            txt.required.set(true)
            md.required.set(false)

            sarif.required.set(true)
        }

        exclude(
            "**/build/*",
            "**/generated/*",
            "**/build.gradle.kts",
            "**/settings.gradle.kts",
        )

        finalizedBy(reportMerge)
    }

    extensions.configure(DetektExtension::class.java) {
        toolVersion = libs.findVersion("detekt").get().requiredVersion
        config.setFrom(file("$rootDir/.github/detekt.yml"))
        parallel = true

        basePath = rootProject.projectDir.absolutePath

        ignoredBuildTypes = listOf("release")

        if (!isJvmModule()) {
            // By default only main and test sources are included. I've added androidTest too
            source.from("src/androidTest/java", "src/androidTest/kotlin")
        }
    }
}