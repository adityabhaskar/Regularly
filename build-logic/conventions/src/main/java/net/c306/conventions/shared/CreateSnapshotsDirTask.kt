package net.c306.conventions.shared

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.ProjectLayout
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.property
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import javax.inject.Inject

/**
 * Task to create the snapshots directory if it doesn't exist. This is required because as of
 * paparazzi 1.3.5, the verify task fails if a module has the paparazzi plugin applied but no
 * snapshots directory exists.
 *
 * See more in this issue: https://github.com/cashapp/paparazzi/issues/1692
 */
@CacheableTask
abstract class CreateSnapshotsDirTask @Inject constructor(
    private val layout: ProjectLayout,
    objects: ObjectFactory,
) : DefaultTask() {

    @Input
    val snapshotPath: Property<String> = objects.property<String>().convention("src/test/snapshots")

    @OutputDirectory
    val snapshotDir: RegularFileProperty = objects.fileProperty().convention {
        layout.projectDirectory.file(snapshotPath.get()).asFile
    }

    @TaskAction
    fun run() {
        snapshotDir.get().asFile.mkdirs()
    }

    companion object {
        private const val NAME = "createSnapshotsDirTask"

        fun register(project: Project) {
            val createSnapshotsDirTask: TaskProvider<CreateSnapshotsDirTask> =
                project.tasks.register<CreateSnapshotsDirTask>(NAME)

            project.tasks.withType<Test>().configureEach {
                inputs.files(createSnapshotsDirTask)
            }
        }
    }
}