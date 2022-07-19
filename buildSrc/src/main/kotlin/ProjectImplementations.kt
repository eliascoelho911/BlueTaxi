import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.designSystemModule() {
    "implementation"(project(":designsystem"))
}

fun DependencyHandlerScope.coreModule() {
    "implementation"(project(":core"))
}

fun DependencyHandlerScope.authModule() {
    "implementation"(project(":auth"))
}