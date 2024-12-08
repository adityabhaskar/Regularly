#!/usr/bin/env kotlin

import java.io.File

val subcommand = args[0]

when (subcommand) {
    "create" -> {
        val moduleName = args.getOrNull(1)
        if (!moduleName.isNullOrBlank()) {
            createModule(moduleName)
        } else {
            println("No module name provided")
        }
    }
    else -> println("$subcommand is not a valid operation")
}

fun createModule(moduleName: String) {
    val moduleDirectory = File(moduleName)
    val gradleSettingsFile = File("settings.gradle.kts")
    val scriptAssetsDirectory = File("./scripts")

    // Check if this module already exists:
    if (moduleDirectory.isDirectory()) {
        println("Cannot create module in '$moduleDirectory', directory already exists")
        return
    }

    // Check Gradle settings file is where we think it is:
    if (!gradleSettingsFile.isFile()) {
        println("Expected Gradle settings at '$gradleSettingsFile', but they're missing")
        return
    }

    // Check the script assets directory is where we think it is:
    if (!scriptAssetsDirectory.isDirectory()) {
        println("Expected script assets at '$scriptAssetsDirectory', but they're missing")
        return
    }

    // Otherwise continue...
    println("Will create module in '$moduleDirectory'...")
    if (moduleDirectory.mkdir()) {
        println("Created '$moduleDirectory' directory")
    } else {
        println("Could not create directory at '$moduleDirectory'")
        return
    }

    // Create the src directory:
    val srcMainDirectory = File(moduleDirectory, "src/main")
    if (srcMainDirectory.mkdirs()) {
        println("Created src directory at '$srcMainDirectory'")
    } else {
        println("Could not create src directory at '$srcMainDirectory'")
        return
    }

    // Create the kotlin directory:
    val kotlinDirectory = File(srcMainDirectory, "kotlin")
    if (kotlinDirectory.mkdirs()) {
        println("Created src directory at '$kotlinDirectory'")
    } else {
        println("Could not create src directory at '$kotlinDirectory'")
        return
    }

    // Create manifest file:
    with(File(srcMainDirectory, "AndroidManifest.xml")) {
        writeText("""<manifest />""")
        println("Create manifest file at '$this'")
    }

    // Create a README for the module:
    with(File(moduleDirectory, "README.md")) {
        writeText(
            """
            # The `$moduleName` module
            
            ## Introduction
            
            TODO: Provide a brief overview of the purpose and contents of this module.
            """.trimIndent(),
        )
        println("Created README at '$this'")
    }

    // Create the build.gradle.kts for the module:
    val packageName = "net.c306.regularly.${moduleName.replace("-", "")}"
    val buildFileContents = File(scriptAssetsDirectory, "template-build.gradle.kts")
        .bufferedReader().use {
            it.readText().replace("NAMESPACE_PLACEHOLDER", packageName)
        }
    File(moduleDirectory, "build.gradle.kts").let { buildGradleFile ->
        buildGradleFile.bufferedWriter().use {
            it.write(buildFileContents)
        }
        println("Created Gradle config at '$buildGradleFile'")
    }

    // Append new module to the list of includes in Gradle settings:
    addModuleToGradleIncludes(gradleSettingsFile, moduleName)
    println("Appended new module to includes in '$gradleSettingsFile'")

    // All done!
    println(
        """
        New module was created successfully at '$moduleDirectory'.
        
        To add this new module as a dependency of another add this to that module's `dependencies`:
        
            implementation(projects.${typeSafeModuleName(moduleName)})"))
        
        """.trimIndent(),
    )
}

private fun typeSafeModuleName(moduleName: String) = moduleName.replace("-\\w".toRegex()) {
    it.value[1].toUpperCase().toString()
}

private fun addModuleToGradleIncludes(gradleSettingsFile: File, moduleName: String) {
    val contents = gradleSettingsFile.readText()
    // Replace the trailing close bracket with ', :"moduleName")'
    // TODO it would be better to do this with a regex of some kind but that should only be
    // necessary if the settings file becomes more complex and this starts to break more often.
    gradleSettingsFile.writeText(
        contents.replace(Regex(pattern = "(\\)\\s*$)"), "    \":$moduleName\",\n$1"),
    )
}