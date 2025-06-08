import io.github.adityabhaskar.dependencygraph.plugin.ShowLegend

plugins {
    alias(libs.plugins.agp.application) apply false
    alias(libs.plugins.agp.library) apply false
    alias(libs.plugins.kgp.android) apply false
    alias(libs.plugins.kgp.jvm) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.google.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.dependencyGraphs)
}

allprojects {
    apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
    extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        format("misc") {
            // define the files to apply `misc` to
            target("*.gradle", ".gitattributes", ".gitignore")

            // define the steps to apply to those files
            trimTrailingWhitespace()
            leadingTabsToSpaces()
        }
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint(libs.versions.ktlint.get())
                .customRuleSets(
                    listOf("io.nlopez.compose.rules:ktlint:0.4.22"),
                )
        }
        kotlinGradle {
            target("*.gradle.kts", "**/*.kts")
            targetExclude("**/build/**/*.kts")
            ktlint(libs.versions.ktlint.get())
                .customRuleSets(
                    listOf("io.nlopez.compose.rules:ktlint:0.4.22"),
                )
        }
        format("xml") {
            target("**/*.xml")
            targetExclude("**/build/**/*.xml")
        }
    }
}

dependencyGraphConfig {
    showLegend.set(ShowLegend.Never)
}